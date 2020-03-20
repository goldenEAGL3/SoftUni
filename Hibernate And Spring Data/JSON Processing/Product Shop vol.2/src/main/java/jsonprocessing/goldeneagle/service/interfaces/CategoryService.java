package jsonprocessing.goldeneagle.service.interfaces;

import jsonprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoriesByCount;
import jsonprocessing.goldeneagle.domain.dto.seedDto.CategorySeedDTO;
import jsonprocessing.goldeneagle.domain.entity.Category;

import java.util.List;

public interface CategoryService {
    long getCountOfCategories();

    void seedCategories(CategorySeedDTO[] categorySeedDTOS);

    Category findCategoryById(int randomCategoryId);

    List<CategoriesByCount> getCategoriesByProductCount();
}
