package org.softuni.springessentialsintro.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuni.springessentialsintro.domain.entity.User;
import org.softuni.springessentialsintro.domain.model.binding.UserLoginBindingModel;
import org.softuni.springessentialsintro.domain.model.binding.UserRegisterBindingModel;
import org.softuni.springessentialsintro.domain.model.service.UserServiceModel;
import org.softuni.springessentialsintro.repository.UserRepository;
import org.softuni.springessentialsintro.utils.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationService validationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationService validationService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        if (isDataInvalid(userRegisterBindingModel)) {
            return false;
        }
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        String hashedPassword = DigestUtils.sha256Hex(userServiceModel.getPassword());
        userServiceModel.setPassword(hashedPassword);

        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null); //TODO: throw exception
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel loginUser(UserLoginBindingModel userLoginBindingModel) {
        User user = this.userRepository.findByUsername(userLoginBindingModel.getUsername()).orElse(null);

        if (user == null) {
            return null;
        }

        String hashedPassword = DigestUtils.sha256Hex(userLoginBindingModel.getPassword());
        if (!user.getPassword().equals(hashedPassword)) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    private boolean isDataInvalid(UserRegisterBindingModel userRegisterBindingModel) {
        String username = userRegisterBindingModel.getUsername();
        String email = userRegisterBindingModel.getEmail();
        String password = userRegisterBindingModel.getPassword();
        String confirmPassword = userRegisterBindingModel.getConfirmPassword();

        return validationService.isEmailInvalid(email) ||
                validationService.isPasswordIncorrect(password, confirmPassword) ||
                validationService.isUsernameTaken(username);
    }

}
