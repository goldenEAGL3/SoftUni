package repository;

import domain.entity.Package;
import domain.entity.enums.Status;

import java.util.List;

public interface PackageRepository {
    void save(Package newPackage);

    List<Package> findAllByStatus(Status status);

    Package findById(String id);

    Package update(Package newPackage);

    List<Package> findAllByUsername(String username);
}
