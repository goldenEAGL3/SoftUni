package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query2.CarFromMakeModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query4.CarsAndPartsModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CarSeedDTOWrapper;

public interface CarService {
    long getCarCount();

    void seedCars(CarSeedDTOWrapper carsSeedDTOs);

    CarFromMakeModelViewWrapper getCarsFromMake(String make);

    CarsAndPartsModelViewWrapper getCarsAndParts();

}
