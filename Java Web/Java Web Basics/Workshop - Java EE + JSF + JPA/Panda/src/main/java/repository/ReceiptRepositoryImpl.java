package repository;

import domain.entity.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {
    private final EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Receipt receipt) {
        entityManager.getTransaction().begin();
        entityManager.persist(receipt);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Receipt> findAllByUsername(String username) {
        List<Receipt> receipts = entityManager.createQuery("SELECT r FROM Receipt r " +
                "WHERE r.recipient.username = :username", Receipt.class)
                .setParameter("username", username)
                .getResultList();
        return receipts;
    }

    @Override
    public Receipt findById(String id) {
        List<Receipt> receipt = entityManager.createQuery("SELECT r FROM Receipt r " +
                "WHERE r.id = :id", Receipt.class)
                .setParameter("id", id)
                .getResultList();
        if (receipt.isEmpty()) {
            return null;
        }
        return receipt.get(0);
    }
}
