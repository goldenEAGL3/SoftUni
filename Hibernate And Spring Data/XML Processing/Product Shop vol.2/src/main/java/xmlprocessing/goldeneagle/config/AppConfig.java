package xmlprocessing.goldeneagle.config;

import xmlprocessing.goldeneagle.utils.DtoConverterImpl;
import xmlprocessing.goldeneagle.utils.FileUtilImpl;
import xmlprocessing.goldeneagle.utils.ValidationUtilImpl;
import xmlprocessing.goldeneagle.utils.XMLSerializerImpl;
import xmlprocessing.goldeneagle.utils.interfaces.FileUtil;
import xmlprocessing.goldeneagle.utils.interfaces.Serializer;
import xmlprocessing.goldeneagle.utils.interfaces.ValidationUtil;
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
