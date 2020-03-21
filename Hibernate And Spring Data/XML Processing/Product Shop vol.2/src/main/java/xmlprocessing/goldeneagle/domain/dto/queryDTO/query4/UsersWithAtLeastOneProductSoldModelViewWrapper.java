package xmlprocessing.goldeneagle.domain.dto.queryDTO.query4;


import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithAtLeastOneProductSoldModelViewWrapper {

    @XmlAttribute(name = "count")
    private int usersCount;
    @XmlElement(name = "user")
    private List<UsersWithAtLeastOneProductSoldModelView> users;

    public int getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UsersWithAtLeastOneProductSoldModelView> getUsers() {
        return this.users;
    }

    public void setUsers(List<UsersWithAtLeastOneProductSoldModelView> users) {
        this.users = users;
    }
}
