package softuni.jsonexercise.domain.dto.seedDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedModelViewWrapper implements Serializable {

    @XmlElement(name = "user")
    private List<UserSeedDTO> users;

    public List<UserSeedDTO> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserSeedDTO> users) {
        this.users = users;
    }
}
