package org.softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.entity.Category;
import org.softuni.productshop.domain.entity.Product;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.softuni.productshop.common.ExceptionsMessages.PRODUCT_NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductServiceModel> findAll() {
        List<ProductServiceModel> allProducts = this.productRepository.findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
        return allProducts;
    }

    @Override
    public ProductServiceModel findById(String id) throws CustomException {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void edit(String id, ProductServiceModel productServiceModel) throws CustomException {
        Product productToBeEdited = this.productRepository.findById(id).orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        Product mappedProduct = this.modelMapper.map(productServiceModel, Product.class);

        editProduct(productToBeEdited, mappedProduct);

        this.productRepository.saveAndFlush(productToBeEdited);


    }

    @Override
    public List<ProductServiceModel> findAllByCategory(CategoryServiceModel categoryServiceModel) {
        Category category = this.modelMapper.map(categoryServiceModel, Category.class);
        List<ProductServiceModel> allByCategory = this.productRepository.findAllByCategory(category)
                .stream()
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
        return allByCategory;
    }

    private void editProduct(Product productToBeEdited, Product mappedProduct) {
        productToBeEdited.setName(mappedProduct.getName());
        productToBeEdited.setDescription(mappedProduct.getDescription());
        productToBeEdited.setPrice(mappedProduct.getPrice());
        productToBeEdited.setCategories(mappedProduct.getCategories());
    }
}
