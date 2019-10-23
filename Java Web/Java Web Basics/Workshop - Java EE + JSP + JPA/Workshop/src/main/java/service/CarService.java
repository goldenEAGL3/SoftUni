package service;

import domain.entity.enums.Engine;
import domain.service.CarServiceModel;


import java.util.List;

public interface CarService {
    CarServiceModel create(String brand, String model, String year, Engine engine, String username);

    List<CarServiceModel> getAll();
}
