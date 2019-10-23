package service;

import domain.entity.User;
import domain.entity.enums.Engine;
import domain.service.CarServiceModel;
import domain.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.CarRepository;
import repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CarServiceModel create(String brand, String model, String year, Engine engine, String username) {
        if (brand.isEmpty() || model.isEmpty() || year.isEmpty()) {
            return null;
        }

        CarServiceModel car = new CarServiceModel();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(Integer.parseInt(year));
        car.setEngine(engine);
        car.setUser(username);

        carRepository.create(car);
        return car;
    }

    @Override
    public List<CarServiceModel> getAll() {
        List<CarServiceModel> cars = carRepository.getAll()
                .stream()
                .map(car -> {
                    CarServiceModel newCar = modelMapper.map(car, CarServiceModel.class);
                    newCar.setUser(car.getUser().getUsername());
                    return newCar;
                })
                .collect(Collectors.toList());
        return cars;
    }
}
