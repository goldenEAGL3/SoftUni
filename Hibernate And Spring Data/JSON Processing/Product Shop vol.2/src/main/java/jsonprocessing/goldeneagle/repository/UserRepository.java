package jsonprocessing.goldeneagle.repository;

import jsonprocessing.goldeneagle.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int randomId);


}
