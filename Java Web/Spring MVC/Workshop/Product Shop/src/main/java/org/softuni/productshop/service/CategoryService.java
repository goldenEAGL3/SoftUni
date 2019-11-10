package org.softuni.productshop.service;

import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.binding.CategoryBindingModel;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    void add(CategoryServiceModel categoryServiceModel) throws CustomException;

    List<CategoryServiceModel> findAll();

    CategoryServiceModel findById(String id) throws CustomException;

    void edit(String id, CategoryBindingModel category) throws CustomException;

    void delete(String id);

    CategoryServiceModel findByName(String beverages);
}
