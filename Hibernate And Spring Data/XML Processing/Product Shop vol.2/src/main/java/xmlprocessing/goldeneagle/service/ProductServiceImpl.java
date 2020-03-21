package xmlprocessing.goldeneagle.service;

import xmlprocessing.goldeneagle.domain.dto.queryDTO.query1.ProductInRangeModelView;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query1.ProductInRangeModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.seedDto.ProductSeedDTO;
import xmlprocessing.goldeneagle.domain.dto.seedDto.ProductSeedDTOWrapper;
import xmlprocessing.goldeneagle.domain.entity.Category;
import xmlprocessing.goldeneagle.domain.entity.Product;
import xmlprocessing.goldeneagle.domain.entity.User;
import xmlprocessing.goldeneagle.repository.ProductRepository;
import xmlprocessing.goldeneagle.repository.UserRepository;
import xmlprocessing.goldeneagle.service.interfaces.ProductService;
import xmlprocessing.goldeneagle.utils.interfaces.DtoConverter;
import xmlprocessing.goldeneagle.utils.interfaces.RandomUtil;
import xmlprocessing.goldeneagle.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void seedProducts(ProductSeedDTOWrapper productSeedDTOS) {
        for (ProductSeedDTO seedDTO : productSeedDTOS.getProducts()) {
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
    public ProductInRangeModelViewWrapper getAllProductsInRangeWithoutBuyer(BigDecimal priceAfter, BigDecimal priceBefore) {
        List<Product> products = this.productRepository
                .findAllByPriceAfterAndPriceBeforeAndBuyerIsNullOrderByPrice(priceAfter, priceBefore);
        List<ProductInRangeModelView> result = new ArrayList<>();
        for (Product product : products) {
            ProductInRangeModelView newProduct = this.dtoConverter.convert(product, ProductInRangeModelView.class);
            String sellerFullName = getFullName(product);
            newProduct.setSeller(sellerFullName);
            result.add(newProduct);
        }
        ProductInRangeModelViewWrapper productResult = new ProductInRangeModelViewWrapper();
        productResult.setProducts(result);
        return productResult;
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
