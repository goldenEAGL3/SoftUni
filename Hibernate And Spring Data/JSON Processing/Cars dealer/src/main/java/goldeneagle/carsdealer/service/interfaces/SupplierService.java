package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query3.LocalSupplierModelView;
import goldeneagle.carsdealer.domain.dto.seedDTOs.SupplierSeedDTO;

import java.util.List;

public interface SupplierService {
    long getSupplierCount();

    void seedSuppliers(SupplierSeedDTO[] supplierSeedDTOS);

    List<LocalSupplierModelView> getAllLocalSuppliers();

}
