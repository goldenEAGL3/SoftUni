package softuni.workshop.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.importDto.EmployeeDto;
import softuni.workshop.domain.dtos.importDto.EmployeeRootDto;
import softuni.workshop.domain.dtos.jsonDtos.exportDto.EmployeeJsonDto;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.domain.entities.Employee;
import softuni.workshop.domain.entities.Project;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.repository.EmployeeRepository;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final static String EMPLOYEES_XML_PATH_FILE =
            "D:\\Java\\Hibernate\\WorkshopVol2\\SecondWorkshop\\src\\main\\resources\\files\\xmls\\employees.xml";

    private static final String EMPLOYEES_EXPORT_JSON_FILE =
            "D:\\Java\\Hibernate\\WorkshopVol2\\SecondWorkshop\\src\\main\\resources\\files\\jsons\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ProjectRepository projectRepository, XmlParser xmlParser, ValidatorUtil validatorUtil, ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void importEmployees() throws JAXBException {
        EmployeeRootDto employees = this.xmlParser.importXML(EmployeeRootDto.class, EMPLOYEES_XML_PATH_FILE);
        for (EmployeeDto employeeDto : employees.getEmployee()) {
            if (!this.validatorUtil.isValid(employeeDto)) {
                System.out.println(this.validatorUtil.getViolationMessages(employeeDto));
                continue;
            }
            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            Company company = this.companyRepository.findCompanyByName(employeeDto.getProject().getCompany().getName());
            Project project = this.projectRepository.findProjectByName(employeeDto.getProject().getName());

            employee.setProject(project);
            employee.getProject().setCompany(company);

            this.employeeRepository.saveAndFlush(employee);
        }
    }


    @Override
    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_XML_PATH_FILE);
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        StringBuilder sb = new StringBuilder();
        this.employeeRepository.findAllByAgeGreaterThan(25).forEach(empl -> sb.append(String.format("" +
                        "Name: %s %s%n" +
                        "    Age: %s%n" +
                        "    Project name: %s%n",
                empl.getFirstName(),
                empl.getLastName(),
                empl.getAge(),
                empl.getProject().getName())));
        return sb.toString().trim();
    }

    @Override
    public void exportEmployees() throws IOException {
        //TODO
    }


    @Override
    public void exportEmployeesToJson() throws IOException {
        List<EmployeeJsonDto> exportedEmployees = this.employeeRepository
                .findAll()
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeJsonDto.class))
                .collect(Collectors.toList());

        String result = this.gson.toJson(exportedEmployees);
        FileWriter writer = new FileWriter(new File(EMPLOYEES_EXPORT_JSON_FILE));
        writer.write(result);
        writer.close();
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_EXPORT_JSON_FILE);
    }

    @Override
    public boolean areExported() throws IOException {
       return  this.readEmployeesJsonFile().length() > 0;
    }

    @Override
    public String exportEmployeesWithGivenName() {

        StringBuilder sb = new StringBuilder();
        this.employeeRepository.findAllByLastNameStartingWith("M")
                .forEach(empl -> sb.append(String.format("" +
                                "Name: %s %s%n" +
                                "    Age: %s%n" +
                                "    Project name: %s%n",
                        empl.getFirstName(),
                        empl.getLastName(),
                        empl.getAge(),
                        empl.getProject().getName())));
        return sb.toString().trim();
    }

    @Override
    public String exportEmployeesWithGivenProjectName() {
        StringBuilder sb = new StringBuilder();
        this.employeeRepository.findAllByProjectName("GitBuh_Project")
                .forEach(empl -> sb.append(String.format("" +
                                "Name: %s %s%n" +
                                "    Age: %s%n" +
                                "    Project name: %s%n" +
                                "           Project Description: %s%n" +
                                "           Payment: %s%n" +
                                "",
                        empl.getFirstName(),
                        empl.getLastName(),
                        empl.getAge(),
                        empl.getProject().getName(),
                        empl.getProject().getDescription(),
                        empl.getProject().getPayment())));
        return sb.toString().trim();
    }
}
