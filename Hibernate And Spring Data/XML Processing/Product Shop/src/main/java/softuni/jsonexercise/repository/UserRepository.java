package softuni.jsonexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import softuni.jsonexercise.domain.entities.User;

import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);

    @Query(value = "SELECT u FROM User u JOIN Product p ON u.id = p.seller.id WHERE p.buyer.id IS NOT NULL ORDER BY u.lastName, u.firstName")
    Set<User> usersWithAtLeastOneProductSold();

    Set<User> findAllBy();
}
