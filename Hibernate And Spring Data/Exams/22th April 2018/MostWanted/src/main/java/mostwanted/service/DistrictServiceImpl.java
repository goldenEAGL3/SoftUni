package mostwanted.service;
import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static mostwanted.common.Constants.DUPLICATE_DATA_MESSAGE;
import static mostwanted.common.Constants.SUCCESSFUL_IMPORT_MESSAGE;

@Service
public class DistrictServiceImpl implements DistrictService{

    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/districts.json";

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
       return this.fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder sb = new StringBuilder();
        DistrictImportDto[] districtDtos
                = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);
        for (DistrictImportDto districtDto : districtDtos) {

            boolean districtExists = this.districtRepository
                    .findDistrictByName(districtDto.getName()) != null;

            District district = this.modelMapper.map(districtDto, District.class);
            if(!this.validationUtil.isValid(district) || districtExists) {
                sb.append(DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.townRepository.findTownByName(districtDto.getTownName());
//            if(town == null) {
//                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
//                continue;
//            }
            district.setTown(town);
            this.districtRepository.saveAndFlush(district);
            sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                    district.getClass().getSimpleName(),
                    district.getName()));
        }
        return sb.toString().trim();
    }
}
