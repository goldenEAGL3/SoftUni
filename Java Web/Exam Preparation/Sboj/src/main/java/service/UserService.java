package service;

import domain.models.binding.UserLoginBindingModel;
import domain.models.binding.UserRegisterBindingModel;
import domain.models.service.UserServiceModel;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel login(UserLoginBindingModel userLoginBindingModel);

    UserServiceModel findByUsername(String user);

}
