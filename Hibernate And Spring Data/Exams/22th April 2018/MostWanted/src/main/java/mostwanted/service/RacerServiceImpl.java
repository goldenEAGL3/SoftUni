package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static mostwanted.common.Constants.*;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder sb = new StringBuilder();

        RacerImportDto[] racersDto = this.gson.fromJson(racersFileContent, RacerImportDto[].class);
        for (RacerImportDto racerImportDto : racersDto) {
            boolean racerExists = this.racerRepository
                    .findRacerByName(racerImportDto.getName()) != null;

            if (racerExists) {
                sb.append(DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Racer racer = this.modelMapper.map(racerImportDto, Racer.class);
            if (!this.validationUtil.isValid(racer)) {
                sb.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.townRepository.findTownByName(racerImportDto.getHomeTown());
            racer.setHomeTown(town);
            this.racerRepository.saveAndFlush(racer);
            sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                    racer.getClass().getSimpleName(),
                    racer.getName()));
        }
        return sb.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder sb = new StringBuilder();
        List<Racer> racers = this.racerRepository.findAllThatHaveACar();
        racers.forEach(racer -> {
            String ageString = racer.getAge() == null
                    ? String.format("Cars:%n")
                    : String.format("Age: %d%n" +
                    "Cars:%n", racer.getAge());
            sb.append(String.format("" +
                            "Name: %s%n" +
                            "%s",
                    racer.getName(),
                    ageString));
            for (Car car : racer.getCars()) {
                sb.append(String.format("   %s %s %d%n",
                        car.getBrand(),
                        car.getModel(),
                        car.getYearOfProduction()));
            }
        });
        return sb.toString().trim();
    }
}
