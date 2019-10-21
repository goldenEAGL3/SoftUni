package repository;

import domain.entity.User;

import java.util.List;


public interface UserRepository {
    void save(User user);

    long getSize();

    User findByUsername(String username);

    List<User> findAll();
}
