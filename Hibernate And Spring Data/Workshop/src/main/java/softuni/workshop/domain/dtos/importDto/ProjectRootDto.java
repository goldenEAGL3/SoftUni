package softuni.workshop.domain.dtos.importDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectRootDto implements Serializable {

    @XmlElement(name = "project")
    private List<ProjectDto> project;

    public List<ProjectDto> getProject() {
        return this.project;
    }

    public void setProject(List<ProjectDto> project) {
        this.project = project;
    }
}
