package goldeneagle.carsdealer.config;


import goldeneagle.carsdealer.utils.DtoConverterImpl;
import goldeneagle.carsdealer.utils.FileUtilImpl;
import goldeneagle.carsdealer.utils.ValidationUtilImpl;
import goldeneagle.carsdealer.utils.XMLSerializerImpl;
import goldeneagle.carsdealer.utils.interfaces.FileUtil;
import goldeneagle.carsdealer.utils.interfaces.Serializer;
import goldeneagle.carsdealer.utils.interfaces.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class AppConfig {


    @Bean
    public Serializer serializer()  {
        return new XMLSerializerImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator validation() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl(this.validation());
    }


    @Bean
    public DtoConverterImpl dtoConverter() {
        return new DtoConverterImpl(this.modelMapper());
    }

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }
}
