package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.importJSON.BranchDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
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
public class BranchServiceImpl implements BranchService {
    private static final String BRANCHES_IMPORT_JSON_FILE_PATH =
            "D:\\Java\\Hibernate\\Exams\\HibernateInc\\src\\main\\resources\\files\\branches.json";

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCHES_IMPORT_JSON_FILE_PATH);
    }

    @Override
    public String importBranches(String branchesFileContent) {
        StringBuilder sb = new StringBuilder();
        BranchDto[] branchDtos = this.gson.fromJson(branchesFileContent, BranchDto[].class);
        for (BranchDto branchDto : branchDtos) {
            if (!this.validationUtil.isValid(branchDto)) {
                sb.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.townRepository.findTownByName(branchDto.getTown());
            Branch branch = this.modelMapper.map(branchDto, Branch.class);
            branch.setTown(town);

            this.branchRepository.saveAndFlush(branch);
            sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                    branch.getClass().getSimpleName(),
                    branch.getName()))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
