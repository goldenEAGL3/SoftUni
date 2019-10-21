package web.beans;

import domain.models.service.UserServiceModel;
import service.UserService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AllUsersBean {
    private List<UserServiceModel> users;

    private final UserService userService;

    @Inject
    public AllUsersBean(UserService userService) {
        this.userService = userService;
        this.getAllUsers();
    }

    public List<UserServiceModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserServiceModel> users) {
        this.users = users;
    }

    private void getAllUsers() {
        this.users = userService.findAll();
    }
}
