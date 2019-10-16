package repository;

import domain.entity.Car;
import domain.service.CarServiceModel;
import domain.service.UserServiceModel;

import java.util.List;

public interface CarRepository {
    void create(CarServiceModel car);

    List<Car> getAll();
}
