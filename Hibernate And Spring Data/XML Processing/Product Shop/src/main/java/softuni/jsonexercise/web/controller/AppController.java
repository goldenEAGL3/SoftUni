package softuni.jsonexercise.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Controller;
import softuni.jsonexercise.domain.dto.seedDTO.*;
import softuni.jsonexercise.service.interfaces.CategoryService;
import softuni.jsonexercise.service.interfaces.ProductService;
import softuni.jsonexercise.service.interfaces.UserService;
import softuni.jsonexercise.utils.interfaces.FileUtil;
import softuni.jsonexercise.utils.serialize.Serializer;

import javax.transaction.Transactional;

import java.io.IOException;


@Controller
@Transactional
public class AppController implements CommandLineRunner {
    private static final String USERS_FILE_PATH = "/files/users.xml";
    private static final String CATEGORIES_FILE_PATH = "/files/categories.xml";
    private static final String PRODUCTS_FILE_PATH = "/files/products.xml";


    private final Serializer serializer;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private String result;

    @Autowired
    public AppController(Serializer serializer, FileUtil fileUtil, UserService userService, ProductService productService, CategoryService categoryService) {
        this.serializer = serializer;
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
        this.result = this.serializer.serialize(this.userService.getAllUsersInfo());
        System.out.println(this.result);
    }

    private void getAllCategoriesInfo() {
        this.result = this.serializer.serialize(this.categoryService.getAllCategories());
        System.out.println(result);
    }

    private void seedDatabase() {
        this.importUsers();
        this.importCategories();
        this.importProducts();
        this.setUserFriends();

    }

    private void successfullySoldProducts() {
        this.result = this.serializer.serialize(this.userService.getAllUsersWithAtLeastOneProductSold());
        System.out.println(this.result);
    }

    private void productsInRange() throws IOException {
        String content = this.serializer.serialize(this.productService.getAllProductsInRangeWithoutBuyer());
        System.out.println(content);
    }

    private void setUserFriends() {
        this.userService.setFriends();
    }

    private void importProducts() {
        if (this.productService.getCountOfProducts() > 0) {
            return;
        }

        ProductSeedModelViewWrapper productSeedDTOS = this.serializer.deserialize(ProductSeedModelViewWrapper.class, PRODUCTS_FILE_PATH);
        this.productService.seedProducts(productSeedDTOS);
    }

    private void importCategories() {
        if (this.categoryService.getCountOfProducts() > 0) {
            return;
        }
        CategorySeedModelViewWrapper categorySeedDTOS = this.serializer.deserialize(CategorySeedModelViewWrapper.class, CATEGORIES_FILE_PATH);
        this.categoryService.seedCategories(categorySeedDTOS);

    }

    private void importUsers() {
        if (this.userService.getCountOfUsers() > 0) {
            return;
        }

        UserSeedModelViewWrapper userSeedDTOS = this.serializer.deserialize(UserSeedModelViewWrapper.class, USERS_FILE_PATH);
        this.userService.seedUsers(userSeedDTOS);

    }
}
