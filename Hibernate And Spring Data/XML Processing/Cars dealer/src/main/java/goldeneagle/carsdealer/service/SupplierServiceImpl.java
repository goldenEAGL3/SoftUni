package goldeneagle.carsdealer.service;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query3.LocalSupplierModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query3.LocalSupplierModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.seedDTOs.SupplierSeedDTO;
import goldeneagle.carsdealer.domain.dto.seedDTOs.SupplierSeedDTOWrapper;
import goldeneagle.carsdealer.domain.entity.Supplier;
import goldeneagle.carsdealer.repository.SupplierRepository;
import goldeneagle.carsdealer.service.interfaces.SupplierService;
import goldeneagle.carsdealer.utils.interfaces.DtoConverter;
import goldeneagle.carsdealer.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ValidationUtil validationUtil;
    private final DtoConverter dtoConverter;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ValidationUtil validationUtil, DtoConverter dtoConverter) {
        this.supplierRepository = supplierRepository;
        this.validationUtil = validationUtil;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public long getSupplierCount() {
        return this.supplierRepository.count();
    }

    @Override
    public void seedSuppliers(SupplierSeedDTOWrapper supplierSeedDTOS) {
        for (SupplierSeedDTO seedDTO : supplierSeedDTOS.getSuppliers()) {
            if (!this.validationUtil.isValid(seedDTO)) {
                System.out.println(this.validationUtil.violationMessage(seedDTO));
                continue;
            }
            Supplier supplier = this.dtoConverter.convert(seedDTO, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }
    }

    @Override
    @Transactional
    public LocalSupplierModelViewWrapper getAllLocalSuppliers() {
        List<LocalSupplierModelView> suppliers = new ArrayList<>();
        this.supplierRepository.findAll()
                .stream()
                .filter(Supplier::isImporter)
                .forEach(supplier -> {
                    LocalSupplierModelView convert = this.dtoConverter.convert(supplier, LocalSupplierModelView.class);
                    convert.setPartsCount(supplier.getParts().size());
                    suppliers.add(convert);
                });
        LocalSupplierModelViewWrapper suppliersResult = new LocalSupplierModelViewWrapper();
        suppliersResult.setSuppliers(suppliers);
        return suppliersResult;
    }
}
