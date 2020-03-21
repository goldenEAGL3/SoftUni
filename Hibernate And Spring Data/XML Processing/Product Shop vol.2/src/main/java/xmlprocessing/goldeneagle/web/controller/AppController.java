package xmlprocessing.goldeneagle.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query1.ProductInRangeModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query2.UsersWithSuccessfullySoldProductsModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoriesByCountModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoryByNameModelView;
import xmlprocessing.goldeneagle.domain.dto.queryDTO.query4.UsersWithAtLeastOneProductSoldModelViewWrapper;
import xmlprocessing.goldeneagle.domain.dto.seedDto.*;
import xmlprocessing.goldeneagle.service.interfaces.CategoryService;
import xmlprocessing.goldeneagle.service.interfaces.ProductService;
import xmlprocessing.goldeneagle.service.interfaces.UserService;
import xmlprocessing.goldeneagle.utils.interfaces.FileUtil;
import xmlprocessing.goldeneagle.utils.interfaces.Serializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private static final String USERS_FILE_PATH = "/files/users.xml";
    private static final String CATEGORIES_FILE_PATH = "/files/categories.xml";
    private static final String PRODUCTS_FILE_PATH = "/files/products.xml";

    private static final BigDecimal PRICE_AFTER = BigDecimal.valueOf(500);
    private static final BigDecimal PRICE_BEFORE = BigDecimal.valueOf(1000);


    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Serializer serializer;
    private final FileUtil fileUtil;

    private String result;

    @Autowired
    public AppController(UserService userService,
                         ProductService productService,
                         CategoryService categoryService,
                         Serializer serializer,
                         FileUtil fileUtil) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.serializer = serializer;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();
        this.productsInRange();
        this.successfullySoldProducts();
        this.categoriesByProductsCount();
        this.getAllUsersWithAtLeastOneSoldProduct();
    }

    private void getAllUsersWithAtLeastOneSoldProduct() throws IOException {
        UsersWithAtLeastOneProductSoldModelViewWrapper users = this.userService.getAllUsersWithAtLeastOneProductSold();
        this.result = this.serializer.serialize(users);
        this.fileUtil.writeToFile(this.result, "output4");
    }

    private void categoriesByProductsCount() throws IOException {
        CategoriesByCountModelViewWrapper categories = this.categoryService.getCategoriesByProductCount();
        this.result = this.serializer.serialize(categories);
        this.fileUtil.writeToFile(this.result, "output3");
    }

    private void successfullySoldProducts() throws IOException {
        UsersWithSuccessfullySoldProductsModelViewWrapper users = this.userService.getSuccessfullySoldProducts();
        this.result = this.serializer.serialize(users);
        this.fileUtil.writeToFile(this.result, "output2");
    }

    private void productsInRange() throws IOException {
        ProductInRangeModelViewWrapper products = this.productService
                .getAllProductsInRangeWithoutBuyer(AppController.PRICE_AFTER, AppController.PRICE_BEFORE);
        this.result = this.serializer.serialize(products);
        this.fileUtil.writeToFile(this.result, "output1");
    }

    private void seedDatabase() {
        this.importUsers();
        this.importCategories();
        this.importProducts();
        this.setUserFriends();
    }

    private void setUserFriends() {
        this.userService.setFriends();
    }

    private void importProducts() {
        if (this.productService.getCountOfProducts() > 0) {
            return;
        }
        ProductSeedDTOWrapper productSeedDTOS = this.serializer.deserialize(ProductSeedDTOWrapper.class, AppController.PRODUCTS_FILE_PATH);
        this.productService.seedProducts(productSeedDTOS);
    }

    private void importCategories() {
        if (this.categoryService.getCountOfCategories() > 0) {
            return;
        }
        CategorySeedDTOWrapper categorySeedDTOS = this.serializer.deserialize(CategorySeedDTOWrapper.class, AppController.CATEGORIES_FILE_PATH);
        this.categoryService.seedCategories(categorySeedDTOS);
    }

    private void importUsers() {
        if (this.userService.getCountOfUsers() > 0) {
            return;
        }
        UserSeedDTOWrapper userSeedDTOS = this.serializer.deserialize(UserSeedDTOWrapper.class, AppController.USERS_FILE_PATH);
        this.userService.seedUsers(userSeedDTOS);
    }
}
