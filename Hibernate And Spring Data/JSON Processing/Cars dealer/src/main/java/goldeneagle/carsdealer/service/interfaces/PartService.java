package goldeneagle.carsdealer.service.interfaces;

import goldeneagle.carsdealer.domain.dto.seedDTOs.PartsSeedDTO;

public interface PartService {
    long getSupplierCount();

    void seedParts(PartsSeedDTO[] partsSeedDTOs);
}
