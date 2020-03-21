package xmlprocessing.goldeneagle.service;

import xmlprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoriesByCountModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoryByNameModelView;
import xmlprocessing.goldeneagle.domain.dto.seedDto.CategorySeedDTO;
import xmlprocessing.goldeneagle.domain.dto.seedDto.CategorySeedDTOWrapper;
import xmlprocessing.goldeneagle.domain.entity.Category;
import xmlprocessing.goldeneagle.domain.entity.Product;
import xmlprocessing.goldeneagle.repository.CategoryRepository;
import xmlprocessing.goldeneagle.service.interfaces.CategoryService;
import xmlprocessing.goldeneagle.utils.interfaces.DtoConverter;
import xmlprocessing.goldeneagle.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private static final int SCALE = 6;

    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ValidationUtil validationUtil,
                               DtoConverter dtoConverter) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public long getCountOfCategories() {
        return this.categoryRepository.count();
    }

    @Override
    public void seedCategories(CategorySeedDTOWrapper categorySeedDTOS) {
        for (CategorySeedDTO seedDTO : categorySeedDTOS.getCategories()) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }
            Category category = this.dtoConverter.convert(seedDTO, Category.class);
            this.categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public Category findCategoryById(int randomCategoryId) {
        Category category = this.categoryRepository.findById(randomCategoryId).orElse(null);
        return category;
    }

    @Override
    public CategoriesByCountModelViewWrapper getCategoriesByProductCount() {
        List<Category> categories = this.categoryRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getProducts().size() - a.getProducts().size())
                .collect(Collectors.toList());

        List<CategoryByNameModelView> result = this.getCategories(categories);
        CategoriesByCountModelViewWrapper categoriesResult = new CategoriesByCountModelViewWrapper();
        categoriesResult.setCategories(result);
        return categoriesResult;
    }

    private List<CategoryByNameModelView> getCategories(List<Category> categories) {
        List<CategoryByNameModelView> result = new ArrayList<>();
        for (Category cat : categories) {
            CategoryByNameModelView category = this.getConvertedCategory(cat);
            result.add(category);
        }
        return result;
    }

    private CategoryByNameModelView getConvertedCategory(Category cat) {
        CategoryByNameModelView result = new CategoryByNameModelView();
        result.setCategory(cat.getName());

        result.setProductsCount(cat.getProducts().size());
        BigDecimal totalRevenue = this.getTotalPrice(cat.getProducts());
        BigDecimal averagePrice = totalRevenue.divide(
                BigDecimal.valueOf(cat.getProducts().size()),
                CategoryServiceImpl.SCALE,
                RoundingMode.CEILING);

        result.setTotalRevenue(totalRevenue);
        result.setAveragePrice(averagePrice);
        return result;
    }

    private BigDecimal getTotalPrice(Set<Product> products) {
        BigDecimal totalVenue = BigDecimal.valueOf(0);
        for (Product product : products) {
            totalVenue = totalVenue.add(product.getPrice());
        }

        return totalVenue;
    }

}
