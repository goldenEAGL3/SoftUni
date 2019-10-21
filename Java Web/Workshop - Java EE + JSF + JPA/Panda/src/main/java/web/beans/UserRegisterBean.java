package web.beans;


import domain.models.binding.UserRegisterBindingModel;
import domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        UserServiceModel user = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        boolean successfulRegister = userService.register(user);

        if (successfulRegister) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/login.xhtml");
            return;
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/register.xhtml");

    }
}
