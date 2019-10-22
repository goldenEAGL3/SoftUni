package web.beans;


import domain.models.service.JobServiceModel;
import domain.models.view.JobViewModel;
import org.modelmapper.ModelMapper;
import service.JobService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobDetailsBean {

    private JobViewModel jobViewModel;

    private final JobService jobService;
    private final ModelMapper modelMapper;

    @Inject
    public JobDetailsBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.findJob();
    }

    private void findJob() {
        String jobId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("jobId");
        JobServiceModel jobById = this.jobService.findById(jobId);
        this.jobViewModel = this.modelMapper.map(jobById, JobViewModel.class);

    }



    public JobViewModel getJobViewModel() {
        return this.jobViewModel;
    }

    public void setJobViewModel(JobViewModel jobViewModel) {
        this.jobViewModel = jobViewModel;
    }
}
