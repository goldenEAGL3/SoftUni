package repository;

import domain.entity.Car;
import domain.entity.User;
import domain.service.CarServiceModel;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Inject
    public CarRepositoryImpl(EntityManager entityManager, ModelMapper modelMapper, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void create(CarServiceModel car) {
        Car newCar = this.modelMapper.map(car, Car.class);
        User user = userRepository.findByUsername(car.getUser());
        newCar.setUser(user);
        entityManager.getTransaction().begin();
        entityManager.persist(newCar);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = this.entityManager.createQuery("SELECT c FROM Car c", Car.class)
                .getResultList();
        return cars;
    }
}
