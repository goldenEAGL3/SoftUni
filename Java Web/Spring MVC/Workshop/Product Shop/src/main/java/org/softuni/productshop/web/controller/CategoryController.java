package org.softuni.productshop.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.entity.Category;
import org.softuni.productshop.domain.model.binding.CategoryBindingModel;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.domain.model.view.CategoryViewModel;
import org.softuni.productshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView addCategory() {
        return super.view("/views/category/add-category");
    }

    @PostMapping("/add")
    public ModelAndView confirmAddCategory(@ModelAttribute(name = "category") @Valid CategoryBindingModel category,
                                           BindingResult bindingResult,
                                           ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("category", category);
            return super.view("/views/category/add-category", modelAndView);
        }
        try {
            CategoryServiceModel categoryServiceModel = this.modelMapper.map(category, CategoryServiceModel.class);
            this.categoryService.add(categoryServiceModel);
        } catch (CustomException e) {
            return super.view("/views/category/add-category");
        }
        return super.redirect("/home");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView showAllCategories(ModelAndView modelAndView) {
        //TODO: AJAX call.
        List<CategoryViewModel> categories = this.fetchCategories();
        modelAndView.addObject("categories", categories);
        return super.view("/views/category/all-categories", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editCategory(@PathVariable(name = "id") String id, ModelAndView modelAndView) {

        try {
            showCategory(id, modelAndView);
        } catch (CustomException e) {
            return super.view("/views/category/all-categories");
        }
        return super.view("/views/category/edit-category", modelAndView);

    }

    @PostMapping("/edit/{id}")
    public ModelAndView confirmEdit(@PathVariable(name = "id") String id,
                                    @ModelAttribute @Valid CategoryBindingModel category,
                                    BindingResult bindingResult,
                                    ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("category", category);
            return super.view("/views/category/edit-category", modelAndView);
        }
        try {
            this.categoryService.edit(id, category);
        } catch (CustomException e) {
            return super.view("/views/category/edit-category");
        }
        return super.redirect("/categories/all");

    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteCategory(@PathVariable(name = "id") String id,
                                       ModelAndView modelAndView) {
        try {
            showCategory(id, modelAndView);
        } catch (CustomException e) {
            return super.view("/views/category/all-categories");
        }
        return super.view("/views/category/delete-category", modelAndView);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView confirmDelete(@PathVariable(name = "id") String id) {
        this.categoryService.delete(id);
        return super.redirect("/categories/all");

    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<CategoryViewModel> fetchCategories() {
        List<CategoryViewModel> categories = this.categoryService.findAll()
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryViewModel.class))
                .collect(Collectors.toList());

        return categories;
    }

    private void showCategory(String id, ModelAndView modelAndView) throws CustomException {
        CategoryServiceModel categoryById = this.categoryService.findById(id);
        CategoryViewModel category = this.modelMapper.map(categoryById, CategoryViewModel.class);
        modelAndView.addObject("category", category);
    }

}
