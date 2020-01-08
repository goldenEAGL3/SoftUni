package org.softuni.productshop.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.binding.product.ProductBindingModel;
import org.softuni.productshop.domain.model.binding.product.ProductEditBindingModel;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.domain.model.view.product.*;
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
import java.util.List;

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
            ProductServiceModel productServiceModel = this.modelMapper.map(product, ProductServiceModel.class);
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
        List<ProductViewModel> allProducts = this.productService.findAll();
        modelAndView.addObject("products", allProducts);
        return super.view("/views/product/all-products", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        try {
            ProductServiceModel productById = this.productService.findById(id);
            ProductDetailsViewModel product = this.modelMapper.map(productById, ProductDetailsViewModel.class);
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
            ProductServiceModel productById = this.productService.findById(id);
            ProductEditViewModel product = this.modelMapper.map(productById, ProductEditViewModel.class);
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

        try {
            ProductServiceModel productServiceModel = this.modelMapper.map(product, ProductServiceModel.class);
            this.productService.edit(id, productServiceModel);
        } catch (CustomException e) {
            //TODO: redirect
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
            modelAndView.addObject("product", product);
        } catch (CustomException e) {
            //TODO: redirect
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
    public List<ProductViewModel> fetch(@PathVariable(name = "category") String category) {
        List<ProductViewModel> allByCategory;
        if ("All".equals(category)) {
            allByCategory = this.productService.findAll();
        } else {
            CategoryServiceModel categoryServiceModel = this.categoryService.findByName(category);
            allByCategory = this.productService.findAllByCategory(categoryServiceModel);
        }
        return allByCategory;
    }
}
