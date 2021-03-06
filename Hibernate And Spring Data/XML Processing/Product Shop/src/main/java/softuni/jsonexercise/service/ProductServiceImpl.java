package softuni.jsonexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.jsonexercise.domain.dto.queryDTO.query1.ProductInRangeModelViewWrapper;
import softuni.jsonexercise.domain.dto.seedDTO.ProductSeedModelViewWrapper;
import softuni.jsonexercise.service.interfaces.ProductService;
import softuni.jsonexercise.domain.entities.Category;
import softuni.jsonexercise.domain.entities.Product;
import softuni.jsonexercise.domain.entities.User;
import softuni.jsonexercise.domain.dto.queryDTO.query1.ProductInRangeModelView;
import softuni.jsonexercise.domain.dto.seedDTO.ProductSeedDTO;
import softuni.jsonexercise.repository.CategoryRepository;
import softuni.jsonexercise.repository.ProductRepository;
import softuni.jsonexercise.repository.UserRepository;
import softuni.jsonexercise.utils.interfaces.DtoConverter;
import softuni.jsonexercise.utils.interfaces.ValidationUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ValidationUtil validationUtil;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final DtoConverter dtoConverter;


    @Autowired
    public ProductServiceImpl(ValidationUtil validationUtil, ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, DtoConverter dtoConverter) {
        this.validationUtil = validationUtil;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public void seedProducts(ProductSeedModelViewWrapper productSeedDTOS) {
        for (ProductSeedDTO seedDTO : productSeedDTOS.getProducts()) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }

            Product product = this.dtoConverter.convert(seedDTO, Product.class);

            int randomBuyerId = getRandomId(this.userRepository.count());
            int randomSellerId = getRandomId(this.userRepository.count());

            User buyer = this.getUserOnRandom(randomBuyerId);
            User seller = this.userRepository.findUserById(randomSellerId);

            product.setBuyer(buyer);
            product.setSeller(seller);
            Set<Category> categories = this.getRandomSetOfCategories();
            product.setCategories(categories);
            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public User getUserOnRandom(int randomId) {
        User user = null;

        if (randomId % 3 != 0) {
            user = this.userRepository.findUserById(randomId);
        }

        return user;
    }

    @Override
    public int getRandomId(long maxValue) {
        Random random = new Random();
        return random.nextInt((int) maxValue) + 1;
    }

    @Override
    public Set<Category> getRandomSetOfCategories() {
        int number = getRandomId(this.categoryRepository.count());
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < number; i++) {
            int randomCategoryId = getRandomId(this.categoryRepository.count());
            Category category = this.categoryRepository.findById(randomCategoryId);
            boolean categoryFound = false;
            for (Category currentCategory : categories) {
                if (currentCategory.getName().equals(category.getName())) {
                    categoryFound = true;
                    break;
                }
            }
            if (!categoryFound) {
                categories.add(category);
            }

        }
        return categories;
    }

    @Override
    public long getCountOfProducts() {
        return this.productRepository.count();
    }

    @Override
    public ProductInRangeModelViewWrapper getAllProductsInRangeWithoutBuyer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BigDecimal priceAfter = new BigDecimal(reader.readLine());
        BigDecimal priceBefore = new BigDecimal(reader.readLine());

        List<ProductInRangeModelView> productInRangeDTOs = this.productRepository
                .findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice(priceAfter, priceBefore).stream()
                .map(product -> this.dtoConverter.convert(product, ProductInRangeModelView.class))
                .collect(Collectors.toList());

        ProductInRangeModelViewWrapper products = new ProductInRangeModelViewWrapper(productInRangeDTOs);
        return products;
    }
}
