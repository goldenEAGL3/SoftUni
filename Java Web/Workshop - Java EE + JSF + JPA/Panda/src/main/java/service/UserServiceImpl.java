package service;

import domain.entity.User;
import domain.entity.enums.Role;
import domain.models.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserServiceModel userServiceModel) {
        boolean userExists = userRepository.findByUsername(userServiceModel.getUsername()) != null;

        if (userExists) {
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
}
