package service;

import domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    UserServiceModel login(UserServiceModel userServiceModel);

    List<UserServiceModel> findAll();

    UserServiceModel findByUsername(String username);
}
