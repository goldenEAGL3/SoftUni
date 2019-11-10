package org.softuni.productshop.web.controller;

import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {
    private final CategoryService categoryService;


    @Autowired
    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return super.view("/views/index");
    }


    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(ModelAndView modelAndView) {
        List<String> categories = this.categoryService.findAll()
                .stream()
                .map(CategoryServiceModel::getName)
                .collect(Collectors.toList());
        modelAndView.addObject("categories", categories);
        return super.view("/views/home", modelAndView);
    }
}
