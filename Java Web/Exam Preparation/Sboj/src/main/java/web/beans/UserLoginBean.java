package web.beans;

import domain.models.binding.UserLoginBindingModel;
import domain.models.service.UserServiceModel;
import service.UserService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel userLoginBindingModel;

    private final UserService userService;

    @Inject
    public UserLoginBean(UserService userService) {
        this.userService = userService;
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public void login() throws IOException {
        UserServiceModel loggedInUser = this.userService.login(userLoginBindingModel);

        if (loggedInUser == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            return;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("username", loggedInUser.getUsername());

        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return this.userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }
}
