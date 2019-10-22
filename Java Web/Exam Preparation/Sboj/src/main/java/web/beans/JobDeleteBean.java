package web.beans;

import service.JobService;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobDeleteBean {

    private  final JobService jobService;



    @Inject
    public JobDeleteBean(JobService jobService) {
        this.jobService = jobService;

    }

    public void delete() throws IOException {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("jobId");
        this.jobService.delete(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
    }


}
