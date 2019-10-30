package org.softuni.residentevil.service;

import org.softuni.residentevil.domain.model.service.VirusServiceModel;
import org.softuni.residentevil.domain.model.view.VirusViewModel;

import java.util.List;

public interface VirusService {
    VirusServiceModel spread(VirusServiceModel virusServiceModel);

    List<VirusServiceModel> findAll();

    VirusServiceModel findById(String id);

    void delete(String id);
}
