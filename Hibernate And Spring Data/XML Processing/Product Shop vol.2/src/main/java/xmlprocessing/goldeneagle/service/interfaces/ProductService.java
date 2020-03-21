package xmlprocessing.goldeneagle.service.interfaces;

import xmlprocessing.goldeneagle.domain.dto.queryDTO.query1.ProductInRangeModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.seedDto.ProductSeedDTOWrapper;

import java.math.BigDecimal;

public interface ProductService {
    long getCountOfProducts();

    void seedProducts(ProductSeedDTOWrapper productSeedDTOS);

    ProductInRangeModelViewWrapper getAllProductsInRangeWithoutBuyer(BigDecimal priceAfter, BigDecimal priceBefore);



}
