package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.seedDTOs.PartsSeedDTOWrapper;

public interface PartService {
    long getSupplierCount();

    void seedParts(PartsSeedDTOWrapper partsSeedDTOs);
}
