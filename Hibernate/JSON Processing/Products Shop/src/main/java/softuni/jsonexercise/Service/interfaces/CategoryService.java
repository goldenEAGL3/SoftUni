package softuni.jsonexercise.service.interfaces;

import softuni.jsonexercise.domain.dto.queryDTO.query3.CategoryModelViewDto;
import softuni.jsonexercise.domain.dto.seedDTO.CategorySeedDTO;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDTO[] categorySeedDTOS);

    long getCountOfProducts();

    List<CategoryModelViewDto> getAllCategories();
}
