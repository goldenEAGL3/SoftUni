package goldeneagle.carsdealer.service;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query2.CarFromMakeModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query2.CarFromMakeModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query4.CarsAndPartsModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query4.CarsAndPartsModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CarSeedDTO;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CarSeedDTOWrapper;
import goldeneagle.carsdealer.domain.entity.Car;
import goldeneagle.carsdealer.domain.entity.Part;
import goldeneagle.carsdealer.repository.CarRepository;
import goldeneagle.carsdealer.service.interfaces.CarService;
import goldeneagle.carsdealer.utils.interfaces.DtoConverter;
import goldeneagle.carsdealer.utils.interfaces.RandomUtil;
import goldeneagle.carsdealer.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;
    private final RandomUtil randomUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          ValidationUtil validationUtil,
                          DtoConverter dtoConverter,
                          RandomUtil randomUtil) {
        this.carRepository = carRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
        this.randomUtil = randomUtil;
    }

    @Override
    public long getCarCount() {
        return this.carRepository.count();
    }

    @Override
    public void seedCars(CarSeedDTOWrapper carsSeedDTOs) {
        for (CarSeedDTO seedDTO : carsSeedDTOs.getCars()) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }
            Car car = this.dtoConverter.convert(seedDTO, Car.class);
            List<Part> parts = this.randomUtil.getRandomParts();
            car.setParts(parts);
            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public CarFromMakeModelViewWrapper getCarsFromMake(String make) {
        List<CarFromMakeModelView> cars = new ArrayList<>();
        this.carRepository.findAll()
                .stream()
                .filter(car -> car.getMake().equals(make))
                .sorted((a, b) -> {
                    int sort = a.getModel().compareTo(b.getModel());
                    if (sort == 0) {
                        sort = Double.compare(b.getTravelledDistance(), a.getTravelledDistance());
                    }
                    return sort;
                })
                .forEach(car -> {
            CarFromMakeModelView convert = this.dtoConverter.convert(car, CarFromMakeModelView.class);
            cars.add(convert);
        });
        CarFromMakeModelViewWrapper carResult = new CarFromMakeModelViewWrapper();
        carResult.setCars(cars);
        return carResult;
    }

    @Override
    public CarsAndPartsModelViewWrapper getCarsAndParts() {
        List<CarsAndPartsModelView> cars = new ArrayList<>();
        this.carRepository.findAll()
                .forEach(car -> {
                    CarsAndPartsModelView convert = this.dtoConverter.convert(car, CarsAndPartsModelView.class);
                    cars.add(convert);
                });
        CarsAndPartsModelViewWrapper carsResult = new CarsAndPartsModelViewWrapper();
        carsResult.setCars(cars);
        return carsResult;
    }
}

