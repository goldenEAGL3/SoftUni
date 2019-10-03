package softuni.jsonexercise.service.interfaces;

import softuni.jsonexercise.domain.dto.queryDTO.query3.CategoryModelViewWrapper;
import softuni.jsonexercise.domain.dto.seedDTO.CategorySeedModelViewWrapper;

public interface CategoryService {
    void seedCategories(CategorySeedModelViewWrapper categorySeedDTOS);

    long getCountOfProducts();

    CategoryModelViewWrapper getAllCategories();
}
