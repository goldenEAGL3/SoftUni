package softuni.jsonexercise.domain.dto.queryDTO.query4;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserViewModelWrapperDtoQuery4 implements Serializable {

    @Expose
    private Integer userCount;
    @Expose
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
