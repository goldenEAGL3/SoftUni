package org.softuni.springessentialsintro.service;

import org.softuni.springessentialsintro.domain.model.binding.UserLoginBindingModel;
import org.softuni.springessentialsintro.domain.model.binding.UserRegisterBindingModel;
import org.softuni.springessentialsintro.domain.model.service.UserServiceModel;

public interface UserService {
    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findByUsername(String username);

    UserServiceModel loginUser(UserLoginBindingModel userLoginBindingModel);

}
