package goldeneagle.carsdealer.service;

import goldeneagle.carsdealer.domain.dto.seedDTOs.PartsSeedDTO;
import goldeneagle.carsdealer.domain.entity.Part;
import goldeneagle.carsdealer.domain.entity.Supplier;
import goldeneagle.carsdealer.repository.PartRepository;
import goldeneagle.carsdealer.service.interfaces.PartService;
import goldeneagle.carsdealer.utils.interfaces.DtoConverter;
import goldeneagle.carsdealer.utils.interfaces.RandomUtil;
import goldeneagle.carsdealer.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;

    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;
    private final RandomUtil randomUtil;

    @Autowired
    public PartServiceImpl(PartRepository partRepository,
                           ValidationUtil validationUtil,
                           DtoConverter dtoConverter,
                           RandomUtil randomUtil) {
        this.partRepository = partRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
        this.randomUtil = randomUtil;
    }

    @Override
    public long getSupplierCount() {
        return this.partRepository.count();
    }

    @Override
    public void seedParts(PartsSeedDTO[] partsSeedDTOs) {
        for (PartsSeedDTO seedDTO : partsSeedDTOs) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }
            Part part = this.dtoConverter.convert(seedDTO, Part.class);
            Supplier supplier = this.randomUtil.getRandomSupplier();
            part.setSupplier(supplier);
            this.partRepository.saveAndFlush(part);
        }
    }
}
