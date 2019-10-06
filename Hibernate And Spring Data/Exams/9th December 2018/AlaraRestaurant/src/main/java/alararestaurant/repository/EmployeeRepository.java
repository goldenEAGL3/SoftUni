package alararestaurant.repository;

import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByName(String name);

    List<Employee> findAllByPosition(Position position);
}
