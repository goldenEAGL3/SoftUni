package web.beans;

import domain.models.view.JobViewModel;
import org.modelmapper.ModelMapper;
import service.JobService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllJobsBean {
    private static final String RESOURCE_PATH = "resources/jpg/";
    private static final String MEDICINE_JPG = "medicine.jpg";
    private static final String SECURITY_JPG = "security.jpg";
    private static final String FOOD_JPG = "food.jpg";
    private static final String DOMESTIC_JPG = "domestic.jpg";
    private static final String CAR_JPG = "car.jpg";
    private List<JobViewModel> jobViewModels;

    private final JobService jobService;
    private final ModelMapper modelMapper;

    @Inject
    public AllJobsBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.findAllJobs();
    }

    private void findAllJobs() {
        this.jobViewModels = this.jobService.findAll()
                .stream().map(job -> {
                    JobViewModel jobViewModel = this.modelMapper.map(job, JobViewModel.class);
                    this.setImgResource(jobViewModel);
                    return jobViewModel;
                })
                .collect(Collectors.toList());
    }

    private void setImgResource(JobViewModel jobViewModel) {
        switch (jobViewModel.getSector().name()) {
            case "Medicine":
                jobViewModel.setImgResource(RESOURCE_PATH + MEDICINE_JPG);
                break;
            case "Security":
                jobViewModel.setImgResource(RESOURCE_PATH + SECURITY_JPG);
                break;
            case "Food":
                jobViewModel.setImgResource(RESOURCE_PATH + FOOD_JPG);
                break;
            case "Domestic":
                jobViewModel.setImgResource(RESOURCE_PATH + DOMESTIC_JPG);
                break;
            case "Car":
                jobViewModel.setImgResource(RESOURCE_PATH + CAR_JPG);
                break;
        }

    }

    public List<JobViewModel> getJobViewModels() {
        return this.jobViewModels;
    }

    public void setJobViewModels(List<JobViewModel> jobViewModels) {
        this.jobViewModels = jobViewModels;
    }
}
