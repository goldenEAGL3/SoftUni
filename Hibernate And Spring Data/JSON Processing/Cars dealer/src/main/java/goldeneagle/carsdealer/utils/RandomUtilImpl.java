package goldeneagle.carsdealer.utils;

import goldeneagle.carsdealer.domain.entity.Car;
import goldeneagle.carsdealer.domain.entity.Customer;
import goldeneagle.carsdealer.domain.entity.Part;
import goldeneagle.carsdealer.domain.entity.Supplier;
import goldeneagle.carsdealer.repository.CarRepository;
import goldeneagle.carsdealer.repository.CustomerRepository;
import goldeneagle.carsdealer.repository.PartRepository;
import goldeneagle.carsdealer.repository.SupplierRepository;
import goldeneagle.carsdealer.utils.interfaces.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RandomUtilImpl implements RandomUtil {
    private static final int CAR_PARTS_LOWER_BOUND = 10;

    private Random random;

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RandomUtilImpl(SupplierRepository supplierRepository,
                          PartRepository partRepository,
                          CarRepository carRepository,
                          CustomerRepository customerRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.random = new Random();
    }

    @Override
    public Supplier getRandomSupplier() {
        int randomSupplierId = this.getRandomNumber(this.supplierRepository.count());
        return this.supplierRepository
                .findById(randomSupplierId)
                .orElse(null);
    }

    @Override
    public List<Part> getRandomParts() {
        List<Part> result = new ArrayList<>();
        int randomIterableNumber = this.getRandomNumber(RandomUtilImpl.CAR_PARTS_LOWER_BOUND) + RandomUtilImpl.CAR_PARTS_LOWER_BOUND; // randomIterableNumber must be between 10 and 20 inclusive
        for (int i = 0; i < randomIterableNumber; i++) {
            int randomPartId = this.getRandomNumber(this.partRepository.count());
            Part part = this.partRepository
                    .findById(randomPartId)
                    .orElse(null);
            result.add(part);
        }
        return result;
    }

    @Override
    public int getRandomNumber(long value) {
        return random.nextInt((int) value) + 1;
    }

    @Override
    public Car getRandomCar() {
        int randomCarId = this.getRandomNumber(this.carRepository.count());
        Car car = this.carRepository.findById(randomCarId).orElse(null);
        return car;
    }

    @Override
    public Customer getRandomCustomer() {
        int randomCustomerId = this.getRandomNumber(this.customerRepository.count());
        Customer customer = this.customerRepository.findById(randomCustomerId).orElse(null);
        return customer;
    }
}
