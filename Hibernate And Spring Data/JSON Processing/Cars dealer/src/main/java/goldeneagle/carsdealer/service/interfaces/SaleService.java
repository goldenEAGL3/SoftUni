package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query6.SalesWithAppliedDiscountModelView;

import java.util.List;

public interface SaleService {
    long getSalesCount();

    void seedSales();

    List<SalesWithAppliedDiscountModelView> getAllSalesWithAppliedDiscount();

}
