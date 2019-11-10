package org.softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.entity.Category;
import org.softuni.productshop.domain.model.binding.CategoryBindingModel;
import org.softuni.productshop.domain.model.service.CategoryServiceModel;
import org.softuni.productshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.softuni.productshop.common.ExceptionsMessages.CATEGORY_ALREADY_EXISTS;
import static org.softuni.productshop.common.ExceptionsMessages.CATEGORY_NOT_FOUND;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(CategoryServiceModel categoryServiceModel) throws CustomException {
        if(this.categoryRepository.findByName(categoryServiceModel.getName()) != null) {
            throw new CustomException(CATEGORY_ALREADY_EXISTS);
        }
        Category category = this.modelMapper.map(categoryServiceModel, Category.class);
        this.categoryRepository.saveAndFlush(category);

    }

    @Override
    public List<CategoryServiceModel> findAll() {
        List<CategoryServiceModel> allCategories = this.categoryRepository.findAll()
                .stream()
                .map(cat -> this.modelMapper.map(cat, CategoryServiceModel.class))
                .collect(Collectors.toList());
        return allCategories;
    }

    @Override
    public CategoryServiceModel findById(String id) throws CustomException {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(CATEGORY_NOT_FOUND));

        return this.modelMapper.map(category, CategoryServiceModel.class);
    }

    @Override
    public void edit(String id, CategoryBindingModel category) throws CustomException {
        Category categoryToBeEdited = this.categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(CATEGORY_NOT_FOUND));

        categoryToBeEdited.setName(category.getName());
        this.categoryRepository.saveAndFlush(categoryToBeEdited);
    }

    @Override
    public void delete(String id) {
        //TODO: check if id actually exists.
        this.categoryRepository.deleteById(id);
    }

    @Override
    public CategoryServiceModel findByName(String categoryName) {
        Category byName = this.categoryRepository.findByName(categoryName);
        return this.modelMapper.map(byName, CategoryServiceModel.class);
    }
}
