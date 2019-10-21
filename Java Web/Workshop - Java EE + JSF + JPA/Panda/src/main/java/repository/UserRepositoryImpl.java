package repository;

import domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public long getSize() {
        Long size = entityManager.createQuery("SELECT count(u) FROM User u", Long.class)
                .getSingleResult();
        return size;
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.username =:username", User.class)
                .setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createQuery("SELECT u from User u", User.class)
                .getResultList();
        return users;
    }
}
