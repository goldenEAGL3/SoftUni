package jsonprocessing.goldeneagle.domain.dto.queryDTO.query4;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UsersWithAtLeastOneProductSoldModelViewWrapper {

    @Expose
    private int usersCount;
    @Expose
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
