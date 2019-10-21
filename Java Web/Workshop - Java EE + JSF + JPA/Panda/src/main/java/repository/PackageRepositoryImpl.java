package repository;

import domain.entity.Package;
import domain.entity.enums.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {
    private final EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Package newPackage) {
        entityManager.getTransaction().begin();
        entityManager.persist(newPackage);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Package> findAllByStatus(Status status) {
        List<Package> packages = entityManager.createQuery("SELECT p FROM Package p WHERE p.status = :status", Package.class)
                .setParameter("status", status)
                .getResultList();
        return packages;
    }

    @Override
    public Package findById(String id) {
        List<Package> packages = entityManager.createQuery("SELECT p FROM Package p WHERE p.id = :id", Package.class)
                .setParameter("id", id)
                .getResultList();
        if (packages.isEmpty()) {
            return null;
        }
        return packages.get(0);
    }

    @Override
    public Package update(Package newPackage) {
        entityManager.getTransaction().begin();
        Package merge = entityManager.merge(newPackage);
        entityManager.getTransaction().commit();
        entityManager.close();
        return merge;
    }

    @Override
    public List<Package> findAllByUsername(String username) {
        List<Package> packages = entityManager.createQuery("SELECT p FROM Package p WHERE p.recipient.username = :username",
                Package.class)
                .setParameter("username", username)
                .getResultList();
        return packages;
    }
}
