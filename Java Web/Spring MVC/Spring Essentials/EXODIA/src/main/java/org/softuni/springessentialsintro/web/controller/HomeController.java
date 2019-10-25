package org.softuni.springessentialsintro.web.controller;

import org.softuni.springessentialsintro.domain.model.service.DocumentServiceModel;
import org.softuni.springessentialsintro.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final DocumentService documentService;

    @Autowired
    public HomeController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView) {
        List<DocumentServiceModel> documents = this.documentService.findAll()
                .stream()
                .peek(doc -> {
                    if(doc.getTitle().length() > 12) {
                        doc.setTitle(doc.getTitle().substring(0, 12) + "...");
                    }
                }).collect(Collectors.toList());
        modelAndView.addObject("documents", documents);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    //TODO: authentication.
}
