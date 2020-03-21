package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query6.SalesWithAppliedDiscountModelViewWrapper;

public interface SaleService {
    long getSalesCount();

    void seedSales();

    SalesWithAppliedDiscountModelViewWrapper getAllSalesWithAppliedDiscount();

}
