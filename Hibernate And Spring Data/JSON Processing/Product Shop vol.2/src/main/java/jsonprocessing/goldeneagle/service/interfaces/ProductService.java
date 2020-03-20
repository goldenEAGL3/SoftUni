package jsonprocessing.goldeneagle.service.interfaces;

import jsonprocessing.goldeneagle.domain.dto.queryDTO.query1.ProductInRangeModelView;
import jsonprocessing.goldeneagle.domain.dto.queryDTO.query2.UsersWithSuccessfullySoldProductsModelView;
import jsonprocessing.goldeneagle.domain.dto.seedDto.ProductSeedDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    long getCountOfProducts();

    void seedProducts(ProductSeedDTO[] productSeedDTOS);

    List<ProductInRangeModelView> getAllProductsInRangeWithoutBuyer(BigDecimal priceAfter, BigDecimal priceBefore);



}
