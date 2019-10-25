package org.softuni.springessentialsintro.web.controller;

import org.softuni.springessentialsintro.domain.model.binding.DocumentScheduleBindingModel;
import org.softuni.springessentialsintro.domain.model.service.DocumentServiceModel;
import org.softuni.springessentialsintro.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/schedule")
    public ModelAndView schedule(ModelAndView modelAndView) {
        modelAndView.setViewName("schedule");
        return modelAndView;
    }

    @PostMapping("/schedule")
    public ModelAndView scheduleDocument(@ModelAttribute DocumentScheduleBindingModel documentScheduleBindingModel,
                                         ModelAndView modelAndView) {
        boolean successfulSchedule = this.documentService.schedule(documentScheduleBindingModel);
        if (successfulSchedule) {
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.setViewName("redirect:/schedule");
        }
        return modelAndView;
    }


    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        DocumentServiceModel document = this.documentService.findById(id);
        modelAndView.addObject("document", document);
        modelAndView.setViewName("details");
        return modelAndView;
    }


    @GetMapping("/print/{id}")
    public ModelAndView print(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        DocumentServiceModel documentServiceModel = this.documentService.findById(id);
        if(documentServiceModel != null) {
            modelAndView.addObject("document", documentServiceModel);
            modelAndView.setViewName("print");
        }  else {
            //TODO: throw exception or redirect.
        }

        return modelAndView;
    }

    @PostMapping("/print/{id}")
    public ModelAndView removeDocument(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        boolean successfulPrinting = this.documentService.print(id);
        if(successfulPrinting) {
            modelAndView.setViewName("redirect:/home");
        }  //TODO: throw exception or redirect.

        return modelAndView;
    }


}
