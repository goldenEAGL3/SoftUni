package service;

import domain.entity.User;
import domain.entity.enums.Role;
import domain.models.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import repository.UserRepository;
import utils.ValidationService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean register(UserServiceModel userServiceModel) {
        boolean invalidData = this.isDataInvalid(userServiceModel);

        if (invalidData) {
            return false;
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        String hashedPassword = DigestUtils.sha256Hex(userServiceModel.getPassword());
        user.setPassword(hashedPassword);

        Role role = this.userRepository.getSize() == 0L ? Role.ADMIN : Role.USER;
        user.setRole(role);

        this.userRepository.save(user);
        return true;
    }

    @Override
    public UserServiceModel login(UserServiceModel userServiceModel) {
        User user = userRepository.findByUsername(userServiceModel.getUsername());

        if (user == null) {
            return null;
        }

        String hashedPassword = DigestUtils.sha256Hex(userServiceModel.getPassword());

        if (!user.getPassword().equals(hashedPassword)) {
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAll() {
        List<UserServiceModel> users = this.userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserServiceModel.class);
    }

    private boolean isDataInvalid(UserServiceModel userServiceModel) {
        String username = userServiceModel.getUsername();
        String email = userServiceModel.getEmail();
        String password = userServiceModel.getPassword();
        String confirmPassword = userServiceModel.getConfirmPassword();

        return validationService.isEmailInvalid(email) ||
                validationService.isPasswordIncorrect(password, confirmPassword) ||
                validationService.isUsernameTaken(username);
    }
}
