package org.softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.entity.Category;
import org.softuni.productshop.domain.entity.Product;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.domain.model.view.product.ProductViewModel;
import org.softuni.productshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.softuni.productshop.common.ExceptionsMessages.PRODUCT_NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ProductServiceModel productServiceModel) throws CustomException {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        Set<Category> categories = this.findAllCategories(productServiceModel.getCategories());
        product.setCategories(categories);
        this.productRepository.saveAndFlush(product);
    }



    @Override
    public List<ProductViewModel> findAll() {
        List<ProductViewModel> allProducts = this.productRepository.findAll()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
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
        Set<Category> categories = this.findAllCategories(productServiceModel.getCategories());
        mappedProduct.setCategories(categories);
        editProduct(productToBeEdited, mappedProduct);

        this.productRepository.saveAndFlush(productToBeEdited);


    }

    @Override
    public List<ProductViewModel> findAllByCategory(CategoryServiceModel categoryServiceModel) {
        Category category = this.modelMapper.map(categoryServiceModel, Category.class);
        List<ProductViewModel> allByCategory = this.productRepository.findAllByCategory(category)
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
        return allByCategory;
    }

    private Set<Category> findAllCategories(Set<String> categoriesString) throws CustomException {
        Set<Category> categories = new HashSet<>();
        for (String currentCategoryId : categoriesString) {
            CategoryServiceModel categoryByName = this.categoryService.findById(currentCategoryId);
            Category category = this.modelMapper.map(categoryByName, Category.class);
            categories.add(category);
        }

        return categories;

    }

    private void editProduct(Product productToBeEdited, Product mappedProduct) {
        productToBeEdited.setName(mappedProduct.getName());
        productToBeEdited.setDescription(mappedProduct.getDescription());
        productToBeEdited.setPrice(mappedProduct.getPrice());
        productToBeEdited.setCategories(mappedProduct.getCategories());
    }
}
