package web.beans;

import domain.models.binding.UserRegisterBindingModel;
import service.UserService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel userRegisterBindingModel;
    private final UserService userService;

    @Inject
    public UserRegisterBean(UserService userService) {
        this.userService = userService;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public void register() throws IOException {
        boolean successfulRegistration = this.userService.register(userRegisterBindingModel);
        if (successfulRegistration) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login.xhtml");
            return;
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("/register.xhtml");
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }
}
