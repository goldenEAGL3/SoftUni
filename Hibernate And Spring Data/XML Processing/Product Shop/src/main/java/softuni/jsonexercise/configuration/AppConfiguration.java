package softuni.jsonexercise.configuration;


import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.jsonexercise.domain.entities.Product;
import softuni.jsonexercise.domain.entities.User;
import softuni.jsonexercise.domain.dto.queryDTO.query1.ProductInRangeModelView;
import softuni.jsonexercise.utils.DtoConverterImpl;
import softuni.jsonexercise.utils.FileUtilImpl;
import softuni.jsonexercise.utils.ValidationUtilImpl;
import softuni.jsonexercise.utils.interfaces.FileUtil;
import softuni.jsonexercise.utils.serialize.Serializer;
import softuni.jsonexercise.utils.serialize.XmlSerializerImpl;

import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class AppConfiguration {

    @Bean
    public Serializer serializer()  {
        return new XmlSerializerImpl();
    }

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidationUtilImpl validationUtil() {
        return new ValidationUtilImpl(this.validator());
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Product, ProductInRangeModelView> typeMap = modelMapper.createTypeMap(Product.class, ProductInRangeModelView.class);

        typeMap.addMappings(mapper ->
                mapper.using(this.sellerFullName).map(Product::getSeller, ProductInRangeModelView::setSeller));

//        TypeMap<Product, SoldProductsModelView> userSoldProductTypeMap = modelMapper.createTypeMap(Product.class, SoldProductsModelView.class);
//
//        userSoldProductTypeMap.addMappings(mapper2 -> {
//               mapper2.map(src -> src.getBuyer().getFirstName(), SoldProductsModelView::setBuyerFirstName);
//                mapper2.map(src -> src.getBuyer().getLastName(), SoldProductsModelView::setBuyerLastName);
//    });
        return modelMapper;
    }

    @Bean
    public DtoConverterImpl dtoConverter() {
        return new DtoConverterImpl(this.modelMapper());
    }


    private Converter<User, String> sellerFullName = s ->
            s.getSource() == null ? null : s.getSource().getFirstName() + " " + s.getSource().getLastName();
}


