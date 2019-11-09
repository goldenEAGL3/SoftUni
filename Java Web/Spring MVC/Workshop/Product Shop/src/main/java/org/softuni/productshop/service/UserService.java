package org.softuni.productshop.service;

import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void register(UserServiceModel userServiceModel) throws CustomException;

    UserServiceModel findById(String id);

    UserServiceModel findByUsername(String username);

    void editProfile(UserServiceModel user, String id, String oldPassword) throws CustomException;

    List<UserServiceModel> findAll();

    void updateRole(String id, String role) throws CustomException;
}
