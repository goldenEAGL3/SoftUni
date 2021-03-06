package org.softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.entity.Role;
import org.softuni.productshop.domain.entity.User;
import org.softuni.productshop.domain.model.service.UserServiceModel;
import org.softuni.productshop.domain.model.view.user.UserViewModel;
import org.softuni.productshop.repository.RoleRepository;
import org.softuni.productshop.repository.UserRepository;
import org.softuni.productshop.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.softuni.productshop.common.ExceptionsMessages.*;

@Service
public class UserServiceImpl implements UserService {
    private static final String ROLE_ROOT = "ROOT";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_MODERATOR = "MODERATOR";
    private static final String ROLE_USER = "USER";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;
    private final ValidationService validationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder encoder, ValidationService validationService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
        this.validationService = validationService;
    }

    @PostConstruct
    public void seedDatabaseWithRoles() {
        if (this.roleRepository.count() == 0) {
            Role roleRoot = new Role();
            roleRoot.setAuthority(ROLE_ROOT);

            Role roleAdmin = new Role();
            roleAdmin.setAuthority(ROLE_ADMIN);

            Role roleModerator = new Role();
            roleModerator.setAuthority(ROLE_MODERATOR);

            Role roleUser = new Role();
            roleUser.setAuthority(ROLE_USER);

            this.roleRepository.saveAndFlush(roleRoot);
            this.roleRepository.saveAndFlush(roleAdmin);
            this.roleRepository.saveAndFlush(roleModerator);
            this.roleRepository.saveAndFlush(roleUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND));
    }

    @Override
    public void register(UserServiceModel userServiceModel) throws CustomException {
        this.validModel(userServiceModel);

        String hashedPassword = encoder.encode(userServiceModel.getPassword());
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(hashedPassword);
        user.setAuthorities(getCorrectAuthority());
        this.userRepository.saveAndFlush(user);

    }

    @Override
    public UserServiceModel findById(String id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(ID_NOT_FOUND));
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND));
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public void editProfile(UserServiceModel user, String id, String oldPassword) throws CustomException {
        User userToBeEdited = this.userRepository.findById(id).orElseThrow(() -> new CustomException(ID_NOT_FOUND));

        if (!userToBeEdited.getEmail().equals(user.getEmail())) {
            this.validateEmail(user.getEmail());
        }
        userToBeEdited.setEmail(user.getEmail());

        //TODO: add check if the user does not want to change his/her password. (if passwords are empty).
        if (!encoder.matches(oldPassword, userToBeEdited.getPassword())) {
            throw new CustomException(OLD_PASSWORD_DOES_NOT_MATCH);
        }
        String hashedPassword = encoder.encode(user.getPassword());
        userToBeEdited.setPassword(hashedPassword);
        this.userRepository.saveAndFlush(userToBeEdited);

    }

    @Override
    public List<UserViewModel> findAll() {
        List<UserViewModel> allUsers = this.userRepository.findAll()
                .stream()
                .map(user -> {
                    UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
                    setAuthorityString(user, userViewModel);
                    return userViewModel;
                })
                .collect(Collectors.toList());
        return allUsers;
    }

    @Override
    public void updateRole(String id, String role) throws CustomException {
        User user = this.userRepository
                .findById(id).orElseThrow(() -> new CustomException(ID_NOT_FOUND));
        this.setCorrectAuthority(user, role);
        this.userRepository.saveAndFlush(user);
    }

    private void setAuthorityString(User user, UserViewModel userViewModel) {
        Set<String> authorities = user.getAuthorities()
                .stream()
                .map(Role::getAuthority)
                .collect(Collectors.toSet());
        userViewModel.setAuthorities(authorities);
    }

    private void setCorrectAuthority(User user, String role) {
        Set<Role> roles = new HashSet<>();

        switch (role) {
            case "ADMIN":
                roles.add(this.roleRepository.findByAuthority(ROLE_ADMIN));
                roles.add(this.roleRepository.findByAuthority(ROLE_MODERATOR));
                break;

            case "MODERATOR":
                roles.add(this.roleRepository.findByAuthority(ROLE_MODERATOR));
                break;
        }
        roles.add(this.roleRepository.findByAuthority(ROLE_USER));
        user.setAuthorities(roles);
    }

    private Set<Role> getCorrectAuthority() {
        Set<Role> authorities = new HashSet<>();
        Role user = this.roleRepository.findByAuthority(ROLE_USER);

        if (this.userRepository.count() == 0) {
            Role root = this.roleRepository.findByAuthority(ROLE_ROOT);
            Role admin = this.roleRepository.findByAuthority(ROLE_ADMIN);
            Role moderator = this.roleRepository.findByAuthority(ROLE_MODERATOR);

            authorities.add(root);
            authorities.add(admin);
            authorities.add(moderator);
            authorities.add(user);
        } else {
            authorities.add(user);
        }
        return authorities;
    }

    private void validModel(UserServiceModel userServiceModel) throws CustomException {
        if (this.validationService.isUsernameTaken(userServiceModel.getUsername())) {
            throw new CustomException(USERNAME_IS_TAKEN);
        }

        validateEmail(userServiceModel.getEmail());
    }

    private void validateEmail(String email) throws CustomException {
        if (this.validationService.isEmailTaken(email)) {
            throw new CustomException(EMAIL_IS_TAKEN);
        }

        if (this.validationService.isEmailInvalid(email)) {
            throw new CustomException(EMAIL_IS_INVALID);
        }
    }


}
