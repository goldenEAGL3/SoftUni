package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query1.OrderedCustomerModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query5.CustomersTotalSalesModelView;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CustomerSeedDTO;

import java.util.List;

public interface CustomerService {
    long getCustomerCount();

    void seedCustomers(CustomerSeedDTO[] customerSeedDTOs);


    List<OrderedCustomerModelView> getOrderedCustomers();

    List<CustomersTotalSalesModelView> getTotalSalesByCustomer();

}
