package softuni.workshop.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.jsonDtos.exportDto.CompanyJsonDto;
import softuni.workshop.domain.dtos.importDto.CompanyDto;
import softuni.workshop.domain.dtos.importDto.CompanyRootDto;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final static String COMPANY_XML_FILE_PATH =
            "D:\\Java\\Hibernate\\WorkshopVol2\\SecondWorkshop\\src\\main\\resources\\files\\xmls\\companies.xml";

    private final static String COMPANY_EXPORT_JSON_FILE =
            "D:\\Java\\Hibernate\\WorkshopVol2\\SecondWorkshop\\src\\main\\resources\\files\\jsons\\companies.json";

    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, XmlParser xmlParser, ValidatorUtil validatorUtil, ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void importCompanies() throws JAXBException {
        CompanyRootDto companies = this.xmlParser.importXML(CompanyRootDto.class, COMPANY_XML_FILE_PATH);
        for (CompanyDto companyDto : companies.getCompany()) {
            if (!this.validatorUtil.isValid(companyDto)) {
                System.out.println(this.validatorUtil.getViolationMessages(companyDto));
                continue;
            }

            Company company = this.modelMapper.map(companyDto, Company.class);
            this.companyRepository.saveAndFlush(company);
        }
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return this.fileUtil.readFile(COMPANY_XML_FILE_PATH);
    }


    @Override
    public void exportJsonCompanies() throws IOException {
        List<CompanyJsonDto> exportCompanies = this.companyRepository
                .findAll()
                .stream()
                .map(comp -> this.modelMapper.map(comp, CompanyJsonDto.class))
                .collect(Collectors.toList());
        String result = this.gson.toJson(exportCompanies);
        FileWriter writer = new FileWriter(new File(COMPANY_EXPORT_JSON_FILE));
        writer.write(result);
        writer.close();

    }

    @Override
    public String readCompaniesJsonFile() throws IOException {
        return this.fileUtil.readFile(COMPANY_EXPORT_JSON_FILE);
    }

    @Override
    public boolean areExported() throws IOException {
        return this.readCompaniesJsonFile().length() > 0;
    }
}
