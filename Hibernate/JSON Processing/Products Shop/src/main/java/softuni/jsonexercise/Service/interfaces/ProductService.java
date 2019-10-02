package softuni.jsonexercise.Service.interfaces;

import softuni.jsonexercise.domain.entities.Category;
import softuni.jsonexercise.domain.entities.User;
import softuni.jsonexercise.dto.queryDTO.query1.ProductInRangeModelView;
import softuni.jsonexercise.dto.seedDTO.ProductSeedDTO;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {
    void seedProducts(ProductSeedDTO[] productSeedDTOS);

    User getUserOnRandom(int randomId);

    int getRandomId(long maxValue);

    Set<Category> getRandomSetOfCategories();

    long getCountOfProducts();

    List<ProductInRangeModelView> getAllProductsInRangeWithoutBuyer() throws IOException;
}
