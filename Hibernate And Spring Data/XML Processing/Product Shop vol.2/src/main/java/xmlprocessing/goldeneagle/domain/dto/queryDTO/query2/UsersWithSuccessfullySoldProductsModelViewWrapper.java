package xmlprocessing.goldeneagle.domain.dto.queryDTO.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSuccessfullySoldProductsModelViewWrapper {

    @XmlElement(name = "user")
    List<UsersWithSuccessfullySoldProductsModelView> users;

    public List<UsersWithSuccessfullySoldProductsModelView> getUsers() {
        return this.users;
    }

    public void setUsers(List<UsersWithSuccessfullySoldProductsModelView> users) {
        this.users = users;
    }
}
