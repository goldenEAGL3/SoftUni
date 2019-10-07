package hiberspring.service;

import hiberspring.domain.dtos.ImportDto.ProductDto;
import hiberspring.domain.dtos.ImportDto.ProductRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static hiberspring.common.Constants.INCORRECT_DATA_MESSAGE;
import static hiberspring.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_IMPORT_JSON_FILE_PATH =
            "D:\\Java\\Hibernate\\Exams\\HibernateInc\\src\\main\\resources\\files\\products.xml";

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, XmlParser xmlParser, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile(PRODUCTS_IMPORT_JSON_FILE_PATH);
    }

    @Override
    public String importProducts() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        ProductRootDto productRootDto = this.xmlParser.parseXml(ProductRootDto.class, PRODUCTS_IMPORT_JSON_FILE_PATH);
        for (ProductDto productDto : productRootDto.getProduct()) {
            if (!this.validationUtil.isValid(productDto)) {
                sb.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Product product = this.modelMapper.map(productDto, Product.class);
            Branch branch = this.branchRepository.findByName(productDto.getBranch());
            product.setBranch(branch);
            this.productRepository.saveAndFlush(product);
            sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                    product.getClass().getSimpleName(),
                    product.getName()))
                    .append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
