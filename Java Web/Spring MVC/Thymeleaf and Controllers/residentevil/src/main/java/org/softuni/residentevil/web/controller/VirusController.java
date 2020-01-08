package org.softuni.residentevil.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entity.enums.Mutation;
import org.softuni.residentevil.domain.model.binding.VirusBindingModel;
import org.softuni.residentevil.domain.model.service.CapitalServiceModel;
import org.softuni.residentevil.domain.model.service.VirusServiceModel;
import org.softuni.residentevil.domain.model.view.CapitalViewModel;
import org.softuni.residentevil.domain.model.view.VirusViewModel;
import org.softuni.residentevil.service.CapitalService;
import org.softuni.residentevil.service.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/virus")
public class VirusController extends BaseController {
    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService, ModelMapper modelMapper) {
        this.capitalService = capitalService;
        this.virusService = virusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView virusAdd(ModelAndView modelAndView, @ModelAttribute(name = "virusBindingModel") VirusBindingModel virusBindingModel) {
        this.setModelData(modelAndView);
        return super.view("add-virus", modelAndView);
    }


    @PostMapping("/add")
    public ModelAndView confirmVirusAdd(@Valid @ModelAttribute(name = "virusBindingModel") VirusBindingModel virusBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject(virusBindingModel);
            return this.virusAdd(modelAndView, virusBindingModel);
        }
        spreadVirus(virusBindingModel);
        return super.redirect("/virus/show");

    }

    @GetMapping("/show")
    public ModelAndView showViruses(ModelAndView modelAndView) {
        List<VirusViewModel> virusViewModels = this.virusService.findAll()
                .stream()
                .map(virus -> this.modelMapper.map(virus, VirusViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("viruses", virusViewModels);
        return super.view("show-viruses", modelAndView);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editVirus(@PathVariable(name = "id") String id,
                                  ModelAndView modelAndView) {
        this.setModelData(modelAndView);
        VirusServiceModel virus = this.virusService.findById(id);
        VirusBindingModel virusBindingModel = this.modelMapper.map(virus, VirusBindingModel.class);
        modelAndView.addObject("virusBindingModel", virusBindingModel);
        return super.view("edit-virus", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView confirmEditVirus(ModelAndView modelAndView,
                                         @PathVariable(name = "id") String id,
                                         @Valid @ModelAttribute(name = "virusBindingModel") VirusBindingModel virusBindingModel,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            this.setModelData(modelAndView);
            modelAndView.addObject("virusBindingModel", virusBindingModel);
            return super.view("edit-virus", modelAndView);
        }
        spreadVirus(virusBindingModel);
        return super.redirect("/virus/show");
    }



    @PostMapping("/delete/{id}")
    public ModelAndView deleteVirus(@PathVariable(name = "id") String id) {
        this.virusService.delete(id);
        return super.redirect("/virus/show");

    }

    private void spreadVirus(@Valid @ModelAttribute(name = "virusBindingModel") VirusBindingModel virusBindingModel) {
        List<CapitalServiceModel> allCapitals = findCapitalsById(virusBindingModel);
        VirusServiceModel virusServiceModel = this.modelMapper.map(virusBindingModel, VirusServiceModel.class);
        virusServiceModel.setCapitals(allCapitals);
        this.virusService.spread(virusServiceModel);
    }

    private void setModelData(ModelAndView modelAndView) {
        Mutation[] mutations = Mutation.values();
        List<CapitalViewModel> capitalNames = findAllCapitals();
        modelAndView.addObject("capitalNames", capitalNames);
        modelAndView.addObject("mutations", mutations);
    }

    private List<CapitalViewModel> findAllCapitals() {
        return this.capitalService.findAll()
                .stream().map(capital -> this.modelMapper.map(capital, CapitalViewModel.class))
                .collect(Collectors.toList());
    }

    private List<CapitalServiceModel> findCapitalsById(VirusBindingModel virusBindingModel) {
        List<CapitalServiceModel> capitals = new ArrayList<>();
        for (String capitalId : virusBindingModel.getCapitals()) {
            CapitalServiceModel capitalServiceModel = this.capitalService.findById(capitalId);
            capitals.add(capitalServiceModel);
        }
        return capitals;
    }


}
