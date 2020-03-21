package xmlprocessing.goldeneagle.domain.dto.seedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedDTOWrapper {

    @XmlElement(name = "user")
    private List<UserSeedDTO> users;

    public List<UserSeedDTO> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserSeedDTO> users) {
        this.users = users;
    }
}
