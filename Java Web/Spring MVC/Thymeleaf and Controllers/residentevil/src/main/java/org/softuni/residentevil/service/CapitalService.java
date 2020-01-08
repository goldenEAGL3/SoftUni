package org.softuni.residentevil.service;

import org.softuni.residentevil.domain.model.service.CapitalServiceModel;
import org.softuni.residentevil.domain.model.service.VirusServiceModel;

import java.util.List;

public interface CapitalService {
    List<CapitalServiceModel> findAll();

    CapitalServiceModel findById(String id);

}
