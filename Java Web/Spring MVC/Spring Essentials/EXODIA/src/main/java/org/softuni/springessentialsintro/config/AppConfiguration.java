package org.softuni.springessentialsintro.config;

import org.modelmapper.ModelMapper;
import org.softuni.springessentialsintro.utils.ValidationService;
import org.softuni.springessentialsintro.utils.ValidationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
