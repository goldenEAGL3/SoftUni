package softuni.jsonexercise.web.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Controller;
import softuni.jsonexercise.Service.interfaces.CategoryService;
import softuni.jsonexercise.Service.interfaces.ProductService;
import softuni.jsonexercise.Service.interfaces.UserService;
import softuni.jsonexercise.dto.queryDTO.query1.ProductInRangeModelView;
import softuni.jsonexercise.dto.queryDTO.query2.UsersWithAtLeastOneProductSoldModelView;
import softuni.jsonexercise.dto.seedDTO.CategorySeedDTO;
import softuni.jsonexercise.dto.seedDTO.ProductSeedDTO;
import softuni.jsonexercise.dto.seedDTO.UserSeedDTO;
import softuni.jsonexercise.utils.interfaces.FileUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
@Transactional
public class AppController implements CommandLineRunner {
    private static final String USERS_FILE_PATH = "C:\\Java\\Java\\Java Databases Hibernate\\Resources\\INTRODUCTION TO HIBERNATE\\jsonexercise\\src\\main\\resources\\files\\users.json";
    private static final String CATEGORIES_FILE_PATH = "C:\\Java\\Java\\Java Databases Hibernate\\Resources\\INTRODUCTION TO HIBERNATE\\jsonexercise\\src\\main\\resources\\files\\categories.json";
    private static final String PRODUCTS_FILE_PATH = "C:\\Java\\Java\\Java Databases Hibernate\\Resources\\INTRODUCTION TO HIBERNATE\\jsonexercise\\src\\main\\resources\\files\\products.json";


    private final Gson gson;
    private final FileUtil fileUtil;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private String result;

    @Autowired
    public AppController(Gson gson, FileUtil fileUtil, UserService userService, ProductService productService, CategoryService categoryService) {
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();
        this.productsInRange();
        this.successfullySoldProducts();
        this.getAllCategoriesInfo();
            this.getAllUsersInfo();
    }

    private void getAllUsersInfo() {
        this.result = this.gson.toJson(this.userService.getAllUsersInfo());
        System.out.println(this.result);
    }

    private void getAllCategoriesInfo() {
        this.result = this.gson.toJson(this.categoryService.getAllCategories());
        System.out.println(result);
    }

    private void seedDatabase() throws IOException {
        this.importUsers();
        this.importCategories();
        this.importProducts();
        this.setUserFriends();

    }

    private void successfullySoldProducts() {
        this.result = this.gson.toJson(this.userService.getAllUsersWithAtLeastOneProductSold());
        System.out.println(this.result);
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeModelView> allProductsInRangeWithoutBuyer = this.productService.getAllProductsInRangeWithoutBuyer();
        String content = this.gson.toJson(allProductsInRangeWithoutBuyer);
        System.out.println(content);
    }

    private void setUserFriends() {
        this.userService.setFriends();
    }

    private void importProducts() throws IOException {
        if (this.productService.getCountOfProducts() > 0) {
            return;
        }

        String content = this.fileUtil.getContent(PRODUCTS_FILE_PATH);
        ProductSeedDTO[] productSeedDTOS = this.gson.fromJson(content, ProductSeedDTO[].class);
        this.productService.seedProducts(productSeedDTOS);
    }

    private void importCategories() throws IOException {
        if (this.categoryService.getCountOfProducts() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(CATEGORIES_FILE_PATH);
        CategorySeedDTO[] categorySeedDTOS = this.gson.fromJson(content, CategorySeedDTO[].class);
        this.categoryService.seedCategories(categorySeedDTOS);

    }

    private void importUsers() throws IOException {
        if (this.userService.getCountOfUsers() > 0) {
            return;
        }

        String content = this.fileUtil.getContent(USERS_FILE_PATH);
        UserSeedDTO[] userSeedDTOS = this.gson.fromJson(content, UserSeedDTO[].class);
        this.userService.seedUsers(userSeedDTOS);

    }
}
