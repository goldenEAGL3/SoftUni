package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query1.OrderedCustomersModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query5.CustomersTotalSalesModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CustomerSeedDTOWrapper;

public interface CustomerService {
    long getCustomerCount();

    void seedCustomers(CustomerSeedDTOWrapper customerSeedDTOs);


    OrderedCustomersModelViewWrapper getOrderedCustomers();

    CustomersTotalSalesModelViewWrapper getTotalSalesByCustomer();

}
