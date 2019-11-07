package org.softuni.productshop.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.binding.UserEditBindingModel;
import org.softuni.productshop.domain.model.view.UserEditProfileViewModel;
import org.softuni.productshop.domain.model.view.UserViewModel;
import org.softuni.productshop.domain.model.binding.UserRegisterBindingModel;
import org.softuni.productshop.domain.model.service.UserServiceModel;
import org.softuni.productshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(@ModelAttribute(name = "userRegisterBindingModel") UserRegisterBindingModel user) {
        return super.view("register");
    }


    // BINDING RESULT ALWAYS COMES AFTER @VALID ATTRIBUTE OR
    // EXCEPTION IS SHOWN TO THE USER WHEN MODEL IS NOT VALID.
    @PostMapping("/register")
    public ModelAndView confirmRegister(@ModelAttribute(name = "userRegisterBindingModel")
                                        @Valid UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView
    ) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user", userRegisterBindingModel);
            return super.view("register", modelAndView);
        }
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        try {
            this.userService.register(userServiceModel);

        } catch (CustomException e) {
            return super.redirect("register");
        }

        return super.redirect("login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return super.view("login");
    }


    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(ModelAndView modelAndView, Principal principal) {
        String username = principal.getName();
        UserServiceModel userServiceModel = this.userService.findByUsername(username);
        UserViewModel user = this.modelMapper.map(userServiceModel, UserViewModel.class);
        modelAndView.addObject("user", user);
        return super.view("profile", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfile(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        UserServiceModel userServiceModel = this.userService.findById(id);
        UserEditProfileViewModel user = this.modelMapper.map(userServiceModel, UserEditProfileViewModel.class);
        modelAndView.addObject("user", user);
        return super.view("edit-profile", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editProfile(@PathVariable(name = "id") String id, @ModelAttribute(name = "user") @Valid UserEditBindingModel user,
                                    BindingResult bindingResult,
                                    ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user", user);
            return super.view("edit-profile", modelAndView);
        }
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        try {
            this.userService.editProfile(userServiceModel, id);
        } catch (CustomException e) {
            //TODO: flash attributes.
            return super.view("edit-profile");
        }
        return super.view("home");
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasAnyAuthority('ROOT_ADMIN', 'ADMIN')")
    public ModelAndView allUsers() {
        //TODO: functionality.
        return super.view("all-users");
    }


}
