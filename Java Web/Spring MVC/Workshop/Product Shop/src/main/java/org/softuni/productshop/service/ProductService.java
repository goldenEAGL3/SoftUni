package org.softuni.productshop.service;

import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.domain.model.view.product.ProductViewModel;

import java.util.List;

public interface ProductService {

    void add(ProductServiceModel productServiceModel) throws CustomException;

    List<ProductViewModel> findAll();

    ProductServiceModel findById(String id) throws CustomException;

    void delete(String id);

    void edit(String id, ProductServiceModel productServiceModel) throws CustomException;

    List<ProductViewModel> findAllByCategory(CategoryServiceModel category);
}
