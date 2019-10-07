package hiberspring.service;

import hiberspring.domain.dtos.ImportDto.EmployeeDto;
import hiberspring.domain.dtos.ImportDto.EmployeeRootDto;
import hiberspring.domain.dtos.ImportDto.ProductDto;
import hiberspring.domain.dtos.ImportDto.ProductRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

import static hiberspring.common.Constants.INCORRECT_DATA_MESSAGE;
import static hiberspring.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_IMPORT_JSON_FILE_PATH =
            "D:\\Java\\Hibernate\\Exams\\HibernateInc\\src\\main\\resources\\files\\employees.xml";

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository, XmlParser xmlParser, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_IMPORT_JSON_FILE_PATH);
    }

    @Override
    public String importEmployees() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        EmployeeRootDto employeeRootDto = this.xmlParser.parseXml(EmployeeRootDto.class, EMPLOYEES_IMPORT_JSON_FILE_PATH);
        for (EmployeeDto employeeDto : employeeRootDto.getEmployee()) {
            boolean empWithCardExists = this.employeeRepository
                    .findEmployeeByEmployeeCard_Number(employeeDto.getCard()) != null;

            EmployeeCard emplCard = this.employeeCardRepository.findEmployeeCardByNumber(employeeDto.getCard());
            Branch branch = this.branchRepository.findByName(employeeDto.getBranch());

            if (!this.validationUtil.isValid(employeeDto) || empWithCardExists || branch == null || emplCard == null) {
                sb.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            employee.setBranch(branch);
            employee.setEmployeeCard(emplCard);

            this.employeeRepository.saveAndFlush(employee);
            sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                    employee.getClass().getSimpleName(),
                    employee.getFirstName() + " " + employee.getLastName()))
                    .append(System.lineSeparator());

        }
        return sb.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        StringBuilder sb = new StringBuilder();
        List<Employee> employees = this.employeeRepository.findAllByBranchWithProducts();
        employees.stream().sorted((a, b) -> {
            String emplNameA = a.getFirstName() + " " + a.getLastName();
            String emplNameB = b.getFirstName() + " " + b.getLastName();
            int sort = emplNameA.compareTo(emplNameB);
            if (sort == 0) {
                int lengthOfPositionA = a.getPosition().length();
                int lengthOfPositionB = b.getPosition().length();
                sort = lengthOfPositionB - lengthOfPositionA;
            }
            return sort;
        }).forEach(empl -> sb.append(String.format("" +
                        "Name: %s%n" +
                        "Position: %s%n" +
                        "Card Number: %s%n",
                empl.getFirstName() + " " +  empl.getLastName(),
                empl.getPosition(),
                empl.getEmployeeCard().getNumber()))
                .append("---------------------------------------")
                .append(System.lineSeparator()));

        return sb.toString();
    }
}
