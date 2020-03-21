package xmlprocessing.goldeneagle.service.interfaces;

import xmlprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoriesByCountModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.seedDto.CategorySeedDTOWrapper;
import xmlprocessing.goldeneagle.domain.entity.Category;

public interface CategoryService {
    long getCountOfCategories();

    void seedCategories(CategorySeedDTOWrapper categorySeedDTOS);

    Category findCategoryById(int randomCategoryId);

    CategoriesByCountModelViewWrapper getCategoriesByProductCount();
}
