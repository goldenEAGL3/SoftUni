package softuni.jsonexercise.domain.dto.queryDTO.query4;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserViewModelWrapperDtoQuery4 implements Serializable {

    @XmlAttribute(name = "count")
    private Integer userCount;

    @XmlElement(name = "user")
    private List<UserViewModelDtoQuery4> users;

    public Integer getUserCount() {
        return this.userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public List<UserViewModelDtoQuery4> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserViewModelDtoQuery4> users) {
        this.users = users;
    }
}
