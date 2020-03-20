package goldeneagle.carsdealer.service;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query6.SalesWithAppliedDiscountModelView;
import goldeneagle.carsdealer.domain.entity.*;
import goldeneagle.carsdealer.repository.SaleRepository;
import goldeneagle.carsdealer.service.interfaces.SaleService;
import goldeneagle.carsdealer.utils.interfaces.DtoConverter;
import goldeneagle.carsdealer.utils.interfaces.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final static int SALES_NUMBER = 350;

    private final SaleRepository saleRepository;

    private final RandomUtil randomUtil;
    private final DtoConverter dtoConverter;


    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           DtoConverter dtoConverter, RandomUtil randomUtil) {
        this.saleRepository = saleRepository;
        this.randomUtil = randomUtil;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public long getSalesCount() {
        return this.saleRepository.count();
    }

    @Override
    public void seedSales() {
        Map<Integer, Car> alreadyAddedCars = new HashMap<>();
        int randomNumberOfSales = this.randomUtil.getRandomNumber(SaleServiceImpl.SALES_NUMBER);
        for (int i = 0; i < randomNumberOfSales; i++) {

            Car car = this.randomUtil.getRandomCar();

            if (alreadyAddedCars.containsKey(car.getId())) {
                continue;
            }

            alreadyAddedCars.put(car.getId(), car);
            Sale sale = new Sale();
            Customer customer = this.randomUtil.getRandomCustomer();

            int numOfDiscounts = Discount.values().length;
            int randomNumber = this.randomUtil.getRandomNumber(numOfDiscounts) - 1;
            Discount disc = Discount.values()[randomNumber];
            double discountPercentage = disc.getNumVal() * 1.0 / 100;

            sale.setCar(car);
            sale.setCustomer(customer);
            sale.setDiscountPercentage(discountPercentage);
            this.saleRepository.saveAndFlush(sale);
        }

    }

    @Override
    public List<SalesWithAppliedDiscountModelView> getAllSalesWithAppliedDiscount() {
        List<SalesWithAppliedDiscountModelView> sales = new ArrayList<>();
        this.saleRepository.findAll()
                .forEach(sale -> {
                    SalesWithAppliedDiscountModelView convert = this.dtoConverter.convert(sale, SalesWithAppliedDiscountModelView.class);
                    BigDecimal price = this.getCarPrice(sale.getCar());
                    convert.setPrice(price);
                    BigDecimal discount = price.multiply(BigDecimal.valueOf(sale.getDiscountPercentage()));
                    BigDecimal priceWithDiscount = price.subtract(discount);
                    convert.setPriceWithDiscount(priceWithDiscount);
                    sales.add(convert);
                });
        return sales;
    }

    private BigDecimal getCarPrice(Car car) {
        return car
                .getParts()
                .stream()
                .map(Part::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
