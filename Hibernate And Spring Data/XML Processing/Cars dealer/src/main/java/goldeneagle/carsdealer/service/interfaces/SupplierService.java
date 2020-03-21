package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query3.LocalSupplierModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.seedDTOs.SupplierSeedDTOWrapper;

public interface SupplierService {
    long getSupplierCount();

    void seedSuppliers(SupplierSeedDTOWrapper supplierSeedDTOS);

    LocalSupplierModelViewWrapper getAllLocalSuppliers();

}
