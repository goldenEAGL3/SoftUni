package org.softuni.productshop.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.softuni.productshop.domain.entity.Category;
import org.softuni.productshop.domain.entity.Order;
import org.softuni.productshop.domain.entity.Product;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.domain.model.view.order.OrderDetailsViewModel;
import org.softuni.productshop.domain.model.view.order.OrderViewModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class AppConfiguration {

    @Bean
    public <T> ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Order.class, OrderViewModel.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getCustomer().getUsername(), OrderViewModel::setCustomerUsername);
                    mapper.map(src -> src.getProduct().getImageUrl(), OrderViewModel::setProductImageUrl);
                });

        modelMapper.createTypeMap(Order.class, OrderDetailsViewModel.class)
                .addMappings(mapper ->
                        mapper.map(src -> src.getCustomer().getUsername(),
                                OrderDetailsViewModel::setCustomerUsername));

        Converter<Set<Category>, Set<String>> categoryNameSet =
                ctx -> ctx.getSource().stream().map(Category::getName)
                .collect(Collectors.toSet());


        modelMapper.createTypeMap(Product.class, ProductServiceModel.class)
                .addMappings(mapper -> mapper.using(categoryNameSet).map(
                        Product::getCategories,
                        ProductServiceModel::setCategories));

        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }


}
