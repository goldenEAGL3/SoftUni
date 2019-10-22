package service;

import domain.entity.User;
import domain.models.binding.UserLoginBindingModel;
import domain.models.binding.UserRegisterBindingModel;
import domain.models.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import repository.UserRepository;
import utils.ValidationService;
//import utils.ValidationService;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ValidationService validationService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        boolean invalidData = isDataInvalid(userRegisterBindingModel);
        if (invalidData) {
            return false;
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        String hashedPassword = DigestUtils.sha256Hex(userServiceModel.getPassword());
        userServiceModel.setPassword(hashedPassword);

        User user = this.modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);
        return true;
    }


    @Override
    public UserServiceModel login(UserLoginBindingModel userLoginBindingModel) {
        User user = this.userRepository.findByUsername(userLoginBindingModel.getUsername());

        if (user == null) {
            return null;
        }
        String hashedPassword = DigestUtils.sha256Hex(userLoginBindingModel.getPassword());
        if (!user.getPassword().equals(hashedPassword)) {
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User desiredUser = this.userRepository.findByUsername(username);
        return this.modelMapper.map(desiredUser, UserServiceModel.class);
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
