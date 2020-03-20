package goldeneagle.carsdealer.utils.interfaces;


import goldeneagle.carsdealer.domain.entity.Car;
import goldeneagle.carsdealer.domain.entity.Customer;
import goldeneagle.carsdealer.domain.entity.Part;
import goldeneagle.carsdealer.domain.entity.Supplier;

import java.util.List;

public interface RandomUtil {
    Supplier getRandomSupplier();

    List<Part> getRandomParts();


    int getRandomNumber(long value);

    Car getRandomCar();

    Customer getRandomCustomer();


//
//    User getRandomUser(int randomId);
//
//    Set<Category> getRandomSetOfCategories();
}
