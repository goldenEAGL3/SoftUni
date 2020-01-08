package org.softuni.residentevil.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.softuni.residentevil.domain.model.binding.VirusBindingModel;
import org.softuni.residentevil.domain.model.service.VirusServiceModel;
import org.softuni.residentevil.util.ValidatorUtil;
import org.softuni.residentevil.util.ValidatorUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class AppBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator validator() {
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Bean
    public ValidatorUtilImpl validationUtils() {
        return new ValidatorUtilImpl(this.validator());
    }


}
