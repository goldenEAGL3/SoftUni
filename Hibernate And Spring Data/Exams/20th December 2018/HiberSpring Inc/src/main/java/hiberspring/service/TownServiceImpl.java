package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.importJSON.TownDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.INCORRECT_DATA_MESSAGE;
import static hiberspring.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_IMPORT_JSON_FILE_PATH =
            "D:\\Java\\Hibernate\\Exams\\HibernateInc\\src\\main\\resources\\files\\towns.json";

    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_IMPORT_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder sb = new StringBuilder();
        TownDto[] townDtos = this.gson.fromJson(townsFileContent, TownDto[].class);
        for (TownDto townDto : townDtos) {
            if (!this.validationUtil.isValid(townDto)) {
                sb.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.modelMapper.map(townDto, Town.class);
            this.townRepository.saveAndFlush(town);
            sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                    town.getClass().getSimpleName(),
                    town.getName()))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
