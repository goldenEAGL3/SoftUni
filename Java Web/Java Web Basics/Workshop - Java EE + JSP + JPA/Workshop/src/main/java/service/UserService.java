package service;


import domain.service.UserServiceModel;

public interface UserService {

     UserServiceModel register(String username, String password, String confirmPassword, String email);

    boolean login(String username, String password);
}
