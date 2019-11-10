package org.softuni.productshop.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.binding.ProductBindingModel;
import org.softuni.productshop.domain.model.binding.ProductEditBindingModel;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.domain.model.view.ProductDeleteViewModel;
import org.softuni.productshop.domain.model.view.ProductEditViewModel;
import org.softuni.productshop.domain.model.view.ProductHomeViewModel;
import org.softuni.productshop.domain.model.view.ProductViewModel;
import org.softuni.productshop.service.CategoryService;
import org.softuni.productshop.service.CloudinaryService;
import org.softuni.productshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView addProduct() {
        return super.view("/views/product/add-product");
    }

    @PostMapping("/add")
    public ModelAndView confirmAdd(@ModelAttribute(name = "product") @Valid ProductBindingModel product,
                                   BindingResult bindingResult,
                                   ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("product", product);
            return super.view("/views/product/add-product", modelAndView);
        }

        try {
            Set<CategoryServiceModel> categories = this.findAllCategories(product.getCategories());
            ProductServiceModel productServiceModel = this.modelMapper.map(product, ProductServiceModel.class);
            productServiceModel.setCategories(categories);
            productServiceModel.setImageUrl(this.cloudinaryService.uploadImage(product.getImageUrl()));
            this.productService.add(productServiceModel);
        } catch (IOException | CustomException e) {
            return super.view("/views/product/add-product");
        }
        return super.redirect("/products/all");
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView showAllProducts(ModelAndView modelAndView) {
        List<ProductViewModel> allProducts = this.productService.findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("products", allProducts);
        return super.view("/views/product/all-products", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        try {
            ProductServiceModel productById = this.productService.findById(id);
            ProductViewModel product = this.modelMapper.map(productById, ProductViewModel.class);
            modelAndView.addObject("product", product);
        } catch (CustomException e) {
            return super.view("/views/product/all-products");
        }

        return super.view("/views/product/details", modelAndView);

    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView edit(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        try {
            ProductServiceModel byId = this.productService.findById(id);
            ProductEditViewModel product = this.modelMapper.map(byId, ProductEditViewModel.class);
            Set<String> categories = byId.getCategories().stream().map(CategoryServiceModel::getName).collect(Collectors.toSet());
            product.setCategories(categories);
            modelAndView.addObject("product", product);
        } catch (CustomException e) {
            return super.redirect("/products/all");
        }
        return super.view("/views/product/edit-product", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView confirmEdit(@PathVariable(name = "id") String id,
                                    @ModelAttribute(name = "product") @Valid ProductEditBindingModel product,
                                    BindingResult bindingResult,
                                    ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("product", product);
            return super.view("/views/product/edit-product", modelAndView);
        }

        ProductServiceModel productServiceModel = this.modelMapper.map(product, ProductServiceModel.class);

        try {
            Set<CategoryServiceModel> categories = this.findAllCategories(product.getCategories());
            productServiceModel.setCategories(categories);
            this.productService.edit(id, productServiceModel);
        } catch (CustomException e) {

        }
        return super.redirect("/products/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView delete(@PathVariable(name = "id") String id,
                               ModelAndView modelAndView) {
        try {
            ProductServiceModel productById = this.productService.findById(id);
            ProductDeleteViewModel product = this.modelMapper.map(productById, ProductDeleteViewModel.class);
            List<String> categoryNames =
                    productById
                            .getCategories()
                            .stream()
                            .map(CategoryServiceModel::getName)
                            .collect(Collectors.toList());
            product.setCategories(categoryNames);
            modelAndView.addObject("product", product);
        } catch (CustomException e) {

        }
        return super.view("/views/product/delete-product", modelAndView);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView confirmDelete(@PathVariable(name = "id") String id) {
        this.productService.delete(id);
        return super.redirect("/products/all");
    }

    @GetMapping("/fetch/{category}")
    @ResponseBody
    public List<ProductHomeViewModel> fetch(@PathVariable(name = "category") String category) {
        List<ProductHomeViewModel> allByCategory;
        if ("All".equals(category)) {
            allByCategory = this.productService.findAll()
                    .stream()
                    .map(product -> this.modelMapper.map(product, ProductHomeViewModel.class))
                    .collect(Collectors.toList());

        } else {
            CategoryServiceModel categoryServiceModel = this.categoryService.findByName(category);
            allByCategory = this.productService.findAllByCategory(categoryServiceModel)
                    .stream()
                    .map(product -> this.modelMapper.map(product, ProductHomeViewModel.class))
                    .collect(Collectors.toList());
        }

        return allByCategory;
    }

    private Set<CategoryServiceModel> findAllCategories(Set<String> categoriesString) throws CustomException {
        Set<CategoryServiceModel> categories = new HashSet<>();
        for (String categoryId : categoriesString) {
            categories.add(this.categoryService.findById(categoryId));
        }
        return categories;
    }
}
