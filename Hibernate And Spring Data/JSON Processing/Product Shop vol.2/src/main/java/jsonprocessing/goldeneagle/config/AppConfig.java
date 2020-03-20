package jsonprocessing.goldeneagle.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jsonprocessing.goldeneagle.utils.DtoConverterImpl;
import jsonprocessing.goldeneagle.utils.FileUtilImpl;
import jsonprocessing.goldeneagle.utils.ValidationUtilImpl;
import jsonprocessing.goldeneagle.utils.interfaces.FileUtil;
import jsonprocessing.goldeneagle.utils.interfaces.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
@Configuration
public class AppConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
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
