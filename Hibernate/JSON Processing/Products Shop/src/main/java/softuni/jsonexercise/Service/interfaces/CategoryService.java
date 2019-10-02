package softuni.jsonexercise.Service.interfaces;

import softuni.jsonexercise.dto.queryDTO.query3.CategoryModelViewDto;
import softuni.jsonexercise.dto.seedDTO.CategorySeedDTO;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDTO[] categorySeedDTOS);

    long getCountOfProducts();

    List<CategoryModelViewDto> getAllCategories();
}
