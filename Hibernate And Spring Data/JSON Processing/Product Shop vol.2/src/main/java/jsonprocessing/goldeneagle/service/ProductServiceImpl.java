package jsonprocessing.goldeneagle.service;

import jsonprocessing.goldeneagle.domain.dto.queryDTO.query1.ProductInRangeModelView;
import jsonprocessing.goldeneagle.domain.dto.seedDto.ProductSeedDTO;
import jsonprocessing.goldeneagle.domain.entity.Category;
import jsonprocessing.goldeneagle.domain.entity.Product;
import jsonprocessing.goldeneagle.domain.entity.User;
import jsonprocessing.goldeneagle.repository.ProductRepository;
import jsonprocessing.goldeneagle.repository.UserRepository;
import jsonprocessing.goldeneagle.service.interfaces.ProductService;
import jsonprocessing.goldeneagle.service.interfaces.UserService;
import jsonprocessing.goldeneagle.utils.interfaces.DtoConverter;
import jsonprocessing.goldeneagle.utils.interfaces.RandomUtil;
import jsonprocessing.goldeneagle.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;
    private final RandomUtil randomUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              UserRepository userRepository,
                              ValidationUtil validationUtil,
                              DtoConverter dtoConverter,
                              RandomUtil randomUtil
    ) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
        this.randomUtil = randomUtil;
    }

    @Override
    public long getCountOfProducts() {
        return this.productRepository.count();
    }

    @Override
    public void seedProducts(ProductSeedDTO[] productSeedDTOS) {
        for (ProductSeedDTO seedDTO : productSeedDTOS) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }

            Product product = this.dtoConverter.convert(seedDTO, Product.class);

            int buyerRandomId = this.randomUtil.getRandomNumber(this.userRepository.count());
            int sellerRandomId = this.randomUtil.getRandomNumber(this.userRepository.count());

            User buyer = this.randomUtil.getRandomUser(buyerRandomId);
            User seller = this.userRepository.findUserById(sellerRandomId);

            product.setBuyer(buyer);
            product.setSeller(seller);

            Set<Category> categories = this.randomUtil.getRandomSetOfCategories();
            product.setCategories(categories);
            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public List<ProductInRangeModelView> getAllProductsInRangeWithoutBuyer(BigDecimal priceAfter, BigDecimal priceBefore) {
        List<Product> products = this.productRepository
                .findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice(priceAfter, priceBefore);
        List<ProductInRangeModelView> result = new ArrayList<>();
        for (Product product : products) {
            ProductInRangeModelView newProduct = this.dtoConverter.convert(product, ProductInRangeModelView.class);
            String sellerFullName = getFullName(product);
            newProduct.setSeller(sellerFullName);
            result.add(newProduct);
        }

        return result;
    }

    private String getFullName(Product product) {
        String firstName = product.getSeller().getFirstName();
        String lastName = product.getSeller().getLastName();
        if (firstName == null) {
            return lastName;
        }
        return firstName + " " + lastName;
    }
}
