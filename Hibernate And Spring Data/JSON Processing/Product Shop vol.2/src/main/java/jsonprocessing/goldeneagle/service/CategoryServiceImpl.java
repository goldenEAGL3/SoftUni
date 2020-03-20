package jsonprocessing.goldeneagle.service;

import jsonprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoriesByCount;
import jsonprocessing.goldeneagle.domain.dto.seedDto.CategorySeedDTO;
import jsonprocessing.goldeneagle.domain.entity.Category;
import jsonprocessing.goldeneagle.domain.entity.Product;
import jsonprocessing.goldeneagle.repository.CategoryRepository;
import jsonprocessing.goldeneagle.service.interfaces.CategoryService;
import jsonprocessing.goldeneagle.utils.interfaces.DtoConverter;
import jsonprocessing.goldeneagle.utils.interfaces.ValidationUtil;
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
    public void seedCategories(CategorySeedDTO[] categorySeedDTOS) {
        for (CategorySeedDTO seedDTO : categorySeedDTOS) {
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
    public List<CategoriesByCount> getCategoriesByProductCount() {
        List<Category> categories = this.categoryRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getProducts().size() - a.getProducts().size())
                .collect(Collectors.toList());

        List<CategoriesByCount> result = this.getCategories(categories);
        return result;
    }

    private List<CategoriesByCount> getCategories(List<Category> categories) {
        List<CategoriesByCount> result = new ArrayList<>();
        for (Category cat : categories) {
            CategoriesByCount category = this.getConvertedCategory(cat);
            result.add(category);
        }
        return result;
    }

    private CategoriesByCount getConvertedCategory(Category cat) {
        CategoriesByCount result = new CategoriesByCount();
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
