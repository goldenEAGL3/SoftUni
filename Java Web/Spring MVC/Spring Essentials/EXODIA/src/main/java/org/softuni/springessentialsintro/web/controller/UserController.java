package org.softuni.springessentialsintro.web.controller;

import org.softuni.springessentialsintro.domain.model.binding.UserLoginBindingModel;
import org.softuni.springessentialsintro.domain.model.binding.UserRegisterBindingModel;
import org.softuni.springessentialsintro.domain.model.service.UserServiceModel;
import org.softuni.springessentialsintro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                     ModelAndView modelAndView) {
        boolean successfulRegistration = this.userService.registerUser(userRegisterBindingModel);
        if(successfulRegistration) {
            modelAndView.setViewName("redirect:/login");
        } else {
            //TODO: addFlashAttributes so that register data is not lost.
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute UserLoginBindingModel userLoginBindingModel,
                                  ModelAndView modelAndView,
                                  HttpSession httpSession) {
        UserServiceModel userServiceModel = this.userService.loginUser(userLoginBindingModel);
        if(userServiceModel == null) {
            //TODO: throw exception or redirect:
        }
        httpSession.setAttribute("username", userServiceModel.getUsername());
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        session.invalidate();

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
