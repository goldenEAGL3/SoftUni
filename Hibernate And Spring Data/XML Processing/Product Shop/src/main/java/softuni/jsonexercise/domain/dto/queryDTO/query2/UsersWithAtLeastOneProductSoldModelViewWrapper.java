package softuni.jsonexercise.domain.dto.queryDTO.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithAtLeastOneProductSoldModelViewWrapper {

    @XmlElement(name = "user")
    private List<UsersWithAtLeastOneProductSoldModelView> users;

    public UsersWithAtLeastOneProductSoldModelViewWrapper(List<UsersWithAtLeastOneProductSoldModelView> users) {
        this.users = users;
    }

    public UsersWithAtLeastOneProductSoldModelViewWrapper() {
    }

    public List<UsersWithAtLeastOneProductSoldModelView> getUsers() {
        return this.users;
    }

    public void setUsers(List<UsersWithAtLeastOneProductSoldModelView> users) {
        this.users = users;
    }
}
