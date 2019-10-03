package softuni.jsonexercise.service.interfaces;

import softuni.jsonexercise.domain.dto.queryDTO.query1.ProductInRangeModelView;
import softuni.jsonexercise.domain.dto.queryDTO.query1.ProductInRangeModelViewWrapper;
import softuni.jsonexercise.domain.dto.seedDTO.ProductSeedModelViewWrapper;
import softuni.jsonexercise.domain.entities.Category;
import softuni.jsonexercise.domain.entities.User;

import java.io.IOException;
import java.util.Set;

public interface ProductService {
      void seedProducts(ProductSeedModelViewWrapper productSeedDTOS);

    User getUserOnRandom(int randomId);

    int getRandomId(long maxValue);

    Set<Category> getRandomSetOfCategories();

    long getCountOfProducts();

    ProductInRangeModelViewWrapper getAllProductsInRangeWithoutBuyer() throws IOException;
}
