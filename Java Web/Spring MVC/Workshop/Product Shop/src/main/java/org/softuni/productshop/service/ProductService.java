package org.softuni.productshop.service;

import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    void add(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAll();

    ProductServiceModel findById(String id) throws CustomException;

    void delete(String id);

    void edit(String id, ProductServiceModel productServiceModel) throws CustomException;

    List<ProductServiceModel> findAllByCategory(CategoryServiceModel category);
}
