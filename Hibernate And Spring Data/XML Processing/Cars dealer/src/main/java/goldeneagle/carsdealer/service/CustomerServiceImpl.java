package goldeneagle.carsdealer.service;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query1.OrderedCustomerModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query1.OrderedCustomersModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query5.CustomersTotalSalesModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query5.CustomersTotalSalesModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CustomerSeedDTO;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CustomerSeedDTOWrapper;
import goldeneagle.carsdealer.domain.entity.Customer;
import goldeneagle.carsdealer.domain.entity.Part;
import goldeneagle.carsdealer.domain.entity.Sale;
import goldeneagle.carsdealer.repository.CustomerRepository;
import goldeneagle.carsdealer.service.interfaces.CustomerService;
import goldeneagle.carsdealer.utils.interfaces.DtoConverter;
import goldeneagle.carsdealer.utils.interfaces.ValidationUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final BigDecimal YOUNG_DRIVER_DISCOUNT_VALUE = BigDecimal.valueOf(0.05);

    private final CustomerRepository customerRepository;

    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ValidationUtil validationUtil,
                               DtoConverter dtoConverter) {
        this.customerRepository = customerRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public long getCustomerCount() {
        return this.customerRepository.count();
    }

    @Override
    public void seedCustomers(CustomerSeedDTOWrapper customerSeedDTOs) {
        for (CustomerSeedDTO seedDTO : customerSeedDTOs.getCustomers()) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }
            Customer customer = this.dtoConverter.convert(seedDTO, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public OrderedCustomersModelViewWrapper getOrderedCustomers() {
        List<OrderedCustomerModelView> result = new ArrayList<>();
        this.customerRepository
                .findAll()
                .stream()
                .sorted((a, b) -> {
                    int sort = a.getBirthDate().compareTo(b.getBirthDate());
                    if (sort == 0) {
                        if (a.isYoungDriver()) {
                            sort = 1;
                        } else if (b.isYoungDriver()) {
                            sort = -1;
                        }
                    }
                    return sort;
                }).forEach(cust -> {
            OrderedCustomerModelView convert = this.dtoConverter.convert(cust, OrderedCustomerModelView.class);
            result.add(convert);
        });
        OrderedCustomersModelViewWrapper customerResult = new OrderedCustomersModelViewWrapper();
        customerResult.setCustomers(result);
        return customerResult;
    }

    @Override
    public CustomersTotalSalesModelViewWrapper getTotalSalesByCustomer() {
        List<CustomersTotalSalesModelView> customers = new ArrayList<>();
        this.customerRepository.findAll()
                .stream()
                .filter(customer -> customer.getSales().size() > 0)
                .forEach(customer -> {
                    CustomersTotalSalesModelView convert = getCustomersTotalSalesModelView(customer);
                    customers.add(convert);
                });

        List<CustomersTotalSalesModelView> orderedCustomers = customers
                .stream()
                .sorted((a, b) -> {
                    int sort = b.getSpentMoney().compareTo(a.getSpentMoney());
                    if (sort == 0) {
                        sort = b.getBoughtCars() - a.getBoughtCars();
                    }
                    return sort;
                }).collect(Collectors.toList());

        CustomersTotalSalesModelViewWrapper customerResult = new CustomersTotalSalesModelViewWrapper();
        customerResult.setCustomers(orderedCustomers);
        return customerResult;
    }

    private CustomersTotalSalesModelView getCustomersTotalSalesModelView(Customer customer) {
        CustomersTotalSalesModelView convert = new CustomersTotalSalesModelView();

        convert.setFullName(customer.getName());
        convert.setBoughtCars(customer.getSales().size());
        BigDecimal totalSpentMoney = this.getTotalSpentMoney(customer.getSales());

        if (customer.isYoungDriver()) {
            BigDecimal discount = totalSpentMoney.multiply(CustomerServiceImpl.YOUNG_DRIVER_DISCOUNT_VALUE);
            totalSpentMoney = totalSpentMoney.subtract(discount);
        }

        convert.setSpentMoney(totalSpentMoney);
        return convert;
    }

    private BigDecimal getTotalSpentMoney(Set<Sale> sales) {
        BigDecimal money = BigDecimal.valueOf(0);
        for (Sale sale : sales) {
            BigDecimal sum = sale
                    .getCar()
                    .getParts()
                    .stream()
                    .map(Part::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal discount = sum.multiply(BigDecimal.valueOf(sale.getDiscountPercentage()));
            sum = sum.subtract(discount);
            money = money.add(sum);
        }
        return money;
    }
}
