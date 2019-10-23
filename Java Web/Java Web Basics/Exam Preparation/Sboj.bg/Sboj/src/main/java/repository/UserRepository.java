package repository;

import domain.entity.User;

public interface UserRepository {
    void save(User user);

    User findByUsername(String username);
}
