package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.importJSON.EmployeeCardDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.INCORRECT_DATA_MESSAGE;
import static hiberspring.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private static final String EMPL_CARDS_IMPORT_JSON_FILE_PATH =
            "D:\\Java\\Hibernate\\Exams\\HibernateInc\\src\\main\\resources\\files\\employee-cards.json";

    private final EmployeeCardRepository employeeCardRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPL_CARDS_IMPORT_JSON_FILE_PATH);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) {
        StringBuilder sb = new StringBuilder();
        EmployeeCardDto[] employeeCardDtos = this.gson.fromJson(employeeCardsFileContent, EmployeeCardDto[].class);
        for (EmployeeCardDto emplCardDto : employeeCardDtos) {
            boolean emplCardExists = this.employeeCardRepository
                    .findEmployeeCardByNumber(emplCardDto.getNumber()) != null;
            if (!this.validationUtil.isValid(emplCardDto) || emplCardExists) {
                sb.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            EmployeeCard empCard = this.modelMapper.map(emplCardDto, EmployeeCard.class);

            this.employeeCardRepository.saveAndFlush(empCard);
            sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                    empCard.getClass().getSimpleName(),
                    empCard.getNumber()))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
