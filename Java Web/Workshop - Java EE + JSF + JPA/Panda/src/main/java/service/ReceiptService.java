package service;

import domain.models.service.ReceiptServiceModel;
import domain.models.view.ReceiptViewModel;

import java.util.List;

public interface ReceiptService {

    void create(String username, String id);

    List<ReceiptServiceModel> findAllByUsername(String username);

    ReceiptServiceModel findById(String id);
}
