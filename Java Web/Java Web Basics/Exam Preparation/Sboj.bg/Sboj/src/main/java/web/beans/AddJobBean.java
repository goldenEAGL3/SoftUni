package web.beans;

import domain.models.binding.JobBindingModel;
import jdk.jfr.Name;
import service.JobService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class AddJobBean {

    private JobBindingModel jobBindingModel;

    private final JobService jobService;

    @Inject
    public AddJobBean(JobService jobService) {
        this.jobService = jobService;
        this.jobBindingModel = new JobBindingModel();
    }

    public void addJob() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String username = (String) session.getAttribute("username");
        this.jobBindingModel.setUser(username);

        boolean successfulAddition = this.jobService.add(jobBindingModel);
//        if(successfulAddition) {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
//        }
//        FacesContext.getCurrentInstance().getExternalContext().redirect("job-add.xhtml");
    }

    public JobBindingModel getJobBindingModel() {
        return this.jobBindingModel;
    }

    public void setJobBindingModel(JobBindingModel jobBindingModel) {
        this.jobBindingModel = jobBindingModel;
    }
}
