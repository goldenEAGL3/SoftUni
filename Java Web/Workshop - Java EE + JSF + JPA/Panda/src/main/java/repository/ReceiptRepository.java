package repository;

import domain.entity.Receipt;

import java.util.List;

public interface ReceiptRepository {
    void save(Receipt receipt);

    List<Receipt> findAllByUsername(String username);

    Receipt findById(String id);
}
