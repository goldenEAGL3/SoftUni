package service;

import domain.entity.enums.Status;
import domain.models.service.PackageServiceModel;

import java.util.List;

public interface PackageService {
    void create(PackageServiceModel packageServiceModel);

    List<PackageServiceModel> findAllByStatus(Status status);

    void changeStatus(String id);

    PackageServiceModel findById(String id);

    List<PackageServiceModel> findByUsername(String username);


}
