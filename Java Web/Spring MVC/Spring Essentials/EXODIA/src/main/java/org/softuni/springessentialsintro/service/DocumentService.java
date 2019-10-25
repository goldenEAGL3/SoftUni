package org.softuni.springessentialsintro.service;

import org.softuni.springessentialsintro.domain.model.binding.DocumentScheduleBindingModel;
import org.softuni.springessentialsintro.domain.model.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    boolean schedule(DocumentScheduleBindingModel documentScheduleBindingModel);

    DocumentServiceModel findById(String id);

    List<DocumentServiceModel> findAll();

    boolean print(String id);
}
