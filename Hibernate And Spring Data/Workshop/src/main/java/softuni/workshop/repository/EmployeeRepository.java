package softuni.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.workshop.domain.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByAgeGreaterThan(Integer age);

    List<Employee> findAllByLastNameStartingWith(String lastNameStartingWith);

    List<Employee> findAllByProjectName(String projectName);
}
