package softuni.jsonexercise.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.Service.interfaces.CategoryService;
import softuni.jsonexercise.domain.entities.Category;
import softuni.jsonexercise.dto.queryDTO.query3.CategoryModelViewDto;
import softuni.jsonexercise.dto.seedDTO.CategorySeedDTO;
import softuni.jsonexercise.repository.CategoryRepository;
import softuni.jsonexercise.utils.interfaces.DtoConverter;
import softuni.jsonexercise.utils.interfaces.ValidationUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, DtoConverter dtoConverter) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
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
    public long getCountOfProducts() {
        return this.categoryRepository.count();
    }

    @Override
    public List<CategoryModelViewDto> getAllCategories() {
        List<CategoryModelViewDto> categories = this.categoryRepository
                .categoriesByProductCount()
                .stream()
                .map(this::setCategoryDtoProperties).collect(Collectors.toList());

        return categories;
    }

    private CategoryModelViewDto setCategoryDtoProperties(Object[] category) {
        CategoryModelViewDto modelViewDto = new CategoryModelViewDto();
        modelViewDto.setName((String) category[0]);
        modelViewDto.setProductsCount(((BigInteger) category[1]).intValue());
        modelViewDto.setAveragePrice((BigDecimal) category[2]);
        modelViewDto.setTotalRevenue((BigDecimal) category[3]);

        return modelViewDto;
    }
}
