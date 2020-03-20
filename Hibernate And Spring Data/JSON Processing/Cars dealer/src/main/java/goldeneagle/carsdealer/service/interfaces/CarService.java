package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query2.CarFromMakeModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query4.CarsAndPartsModelView;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CarSeedDTO;

import java.util.List;

public interface CarService {
    long getCarCount();

    void seedCars(CarSeedDTO[] carsSeedDTOs);

    List<CarFromMakeModelView> getCarsFromMake(String make);

    List<CarsAndPartsModelView> getCarsAndParts();

}
