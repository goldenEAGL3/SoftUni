package softuni.workshop.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.importDto.ProjectDto;
import softuni.workshop.domain.dtos.importDto.ProjectRootDto;
import softuni.workshop.domain.dtos.jsonDtos.exportDto.ProjectJsonDto;
import softuni.workshop.domain.entities.Project;
import softuni.workshop.repository.CompanyRepository;
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
public class ProjectServiceImpl implements ProjectService {

    private static final String PROJECTS_XML_FILE_PATH =
            "D:\\Java\\Hibernate\\WorkshopVol2\\SecondWorkshop\\src\\main\\resources\\files\\xmls\\projects.xml";

    private static final String PROJECT_EXPORT_JSON_FILE =
            "D:\\Java\\Hibernate\\WorkshopVol2\\SecondWorkshop\\src\\main\\resources\\files\\jsons\\projects.json";

    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, CompanyRepository companyRepository, XmlParser xmlParser, ValidatorUtil validatorUtil, ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void importProjects() throws JAXBException {
        ProjectRootDto projects = this.xmlParser.importXML(ProjectRootDto.class, PROJECTS_XML_FILE_PATH);
        for (ProjectDto projectDto : projects.getProject()) {
            if (!this.validatorUtil.isValid(projectDto)) {
                System.out.println(this.validatorUtil.getViolationMessages(projectDto));
                continue;
            }
            Project project = this.modelMapper.map(projectDto, Project.class);
            project.setCompany(this.companyRepository.findCompanyByName(projectDto.getCompany().getName()));
            this.projectRepository.saveAndFlush(project);
        }
    }


    @Override
    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
        return this.fileUtil.readFile(PROJECTS_XML_FILE_PATH);
    }

    @Override
    public String exportFinishedProjects() {
        StringBuilder sb = new StringBuilder();

        this.projectRepository.findAllByIsFinishedIsTrue().forEach(project -> sb.append(String.format("" +
                        "Project Name: %s%n" +
                        "    Description: %s%n" +
                        "    Payment: %s%n",
                project.getName(),
                project.getDescription(),
                project.getPayment())));
        return sb.toString().trim();
    }


    @Override
    public void exportProjectToJson() throws IOException {
        List<ProjectJsonDto> exportedProjects = this.projectRepository
                .findAll()
                .stream()
                .map(project -> this.modelMapper.map(project, ProjectJsonDto.class))
                .collect(Collectors.toList());

        String result = this.gson.toJson(exportedProjects);
        FileWriter writer = new FileWriter(new File(PROJECT_EXPORT_JSON_FILE));
        writer.write(result);
        writer.close();
    }

    @Override
    public String readProjectJsonFile() throws IOException {
        return this.fileUtil.readFile(PROJECT_EXPORT_JSON_FILE);
    }

    @Override
    public boolean areExported() throws IOException {
        return this.readProjectJsonFile().length() > 0;
    }

    @Override
    public String exportProjectsWithNameEnding() {
        StringBuilder sb = new StringBuilder();
        this.projectRepository
                .findAllByNameEndingWith("")
                .forEach(project -> sb.append(String.format("" +
                                "Project name: %s%n" +
                                "   Payment: %s%n" +
                                "   Start date: %s%n" +
                                "   Description: %s%n" +
                                "   Company name: %s%n",
                        project.getName(),
                        project.getPayment(),
                        project.getStartDate(),
                        project.getDescription(),
                        project.getCompany().getName())));

        return sb.toString().trim();
    }
}
