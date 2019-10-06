package alararestaurant.service;


import alararestaurant.domain.dtos.importJSON.EmployeeDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String EMPLOYEES_IMPORT_JSON_FILE_PATH =
            "D:\\Java\\Hibernate\\AlaraRestaurant\\AlaraRestaurant\\src\\main\\resources\\files\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_IMPORT_JSON_FILE_PATH);

    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder sb = new StringBuilder();
        EmployeeDto[] employeesList = this.gson.fromJson(employees, EmployeeDto[].class);
        for (EmployeeDto employeeDto : employeesList) {

            if (!this.validationUtil.isValid(employeeDto)) {
                sb.append("Invalid data format").append(System.lineSeparator());
                continue;
            }
            Position position = this.positionRepository.findPositionByName(employeeDto.getPosition());
            if (position == null) {
                position = new Position(employeeDto.getPosition());
            }

            Employee emp = this.modelMapper.map(employeeDto, Employee.class);
            emp.setPosition(position);
            this.employeeRepository.saveAndFlush(emp);
            sb.append(String.format("Record %s successfully imported.%n", emp.getName()));
        }
        return sb.toString().trim();
    }
}
