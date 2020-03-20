package jsonprocessing.goldeneagle.web.controller;

import com.google.gson.Gson;
import jsonprocessing.goldeneagle.domain.dto.queryDTO.query1.ProductInRangeModelView;
import jsonprocessing.goldeneagle.domain.dto.queryDTO.query2.UsersWithSuccessfullySoldProductsModelView;
import jsonprocessing.goldeneagle.domain.dto.queryDTO.query3.CategoriesByCount;
import jsonprocessing.goldeneagle.domain.dto.queryDTO.query4.UsersWithAtLeastOneProductSoldModelViewWrapper;
import jsonprocessing.goldeneagle.domain.dto.seedDto.CategorySeedDTO;
import jsonprocessing.goldeneagle.domain.dto.seedDto.ProductSeedDTO;
import jsonprocessing.goldeneagle.domain.dto.seedDto.UserSeedDTO;
import jsonprocessing.goldeneagle.service.interfaces.CategoryService;
import jsonprocessing.goldeneagle.service.interfaces.ProductService;
import jsonprocessing.goldeneagle.service.interfaces.UserService;
import jsonprocessing.goldeneagle.utils.interfaces.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private static final String USERS_FILE_PATH = "D:\\Java\\Hibernate\\JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\users.json";
    private static final String CATEGORIES_FILE_PATH = "D:\\Java\\Hibernate\\JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\categories.json";
    private static final String PRODUCTS_FILE_PATH = "D:\\Java\\Hibernate\\JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\products.json";

    private static final BigDecimal PRICE_AFTER = BigDecimal.valueOf(500);
    private static final BigDecimal PRICE_BEFORE = BigDecimal.valueOf(1000);


    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Gson gson;
    private final FileUtil fileUtil;

    private String result;

    @Autowired
    public AppController(UserService userService,
                         ProductService productService,
                         CategoryService categoryService,
                         Gson gson,
                         FileUtil fileUtil) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.gson = gson;
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
        this.result = this.gson.toJson(users);
        this.fileUtil.writeToFile(this.result, "output4");
    }

    private void categoriesByProductsCount() throws IOException {
        List<CategoriesByCount> categories = this.categoryService.getCategoriesByProductCount();
        this.result = this.gson.toJson(categories);
        this.fileUtil.writeToFile(this.result, "output3");
    }

    private void successfullySoldProducts() throws IOException {
        List<UsersWithSuccessfullySoldProductsModelView> users = this.userService.getSuccessfullySoldProducts();
        this.result = this.gson.toJson(users);
        this.fileUtil.writeToFile(this.result, "output2");
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeModelView> products = this.productService
                .getAllProductsInRangeWithoutBuyer(AppController.PRICE_AFTER, AppController.PRICE_BEFORE);
        this.result = this.gson.toJson(products);
        this.fileUtil.writeToFile(this.result, "output1");
    }


    private void seedDatabase() throws IOException {
        this.importUsers();
        this.importCategories();
        this.importProducts();
        this.setUserFriends();
    }

    private void setUserFriends() {
        this.userService.setFriends();
    }

    private void importProducts() throws IOException {
        if (this.productService.getCountOfProducts() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(AppController.PRODUCTS_FILE_PATH);
        ProductSeedDTO[] productSeedDTOS = this.gson.fromJson(content, ProductSeedDTO[].class);
        this.productService.seedProducts(productSeedDTOS);
    }

    private void importCategories() throws IOException {
        if (this.categoryService.getCountOfCategories() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(AppController.CATEGORIES_FILE_PATH);
        CategorySeedDTO[] categorySeedDTOS = this.gson.fromJson(content, CategorySeedDTO[].class);
        this.categoryService.seedCategories(categorySeedDTOS);
    }

    private void importUsers() throws IOException {
        if (this.userService.getCountOfUsers() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(AppController.USERS_FILE_PATH);
        UserSeedDTO[] userSeedDTOS = this.gson.fromJson(content, UserSeedDTO[].class);
        this.userService.seedUsers(userSeedDTOS);
    }
}
