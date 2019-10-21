package service;

import domain.entity.Package;
import domain.entity.Receipt;
import domain.entity.User;
import domain.models.service.PackageServiceModel;
import domain.models.service.ReceiptServiceModel;
import domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.ReceiptRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptServiceImpl implements ReceiptService {
    private static final BigDecimal MULTIPLICATION_CONSTANT = BigDecimal.valueOf(2.67);

    private final UserService userService;
    private final PackageService packageService;
    private final ReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ReceiptServiceImpl(UserService userService, PackageService packageService, ReceiptRepository receiptRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.packageService = packageService;
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(String username, String packageId) {
        boolean invalidData = arePropertiesInvalid(username, packageId);
       if(invalidData) {
           //TODO: throw exception or redirect.
       }

        packageService.changeStatus(packageId);
        PackageServiceModel aPackage = packageService.findById(packageId);
        Package currentPackage = modelMapper.map(aPackage, Package.class);

        UserServiceModel user = userService.findByUsername(username);
        User currentUser = modelMapper.map(user, User.class);

        Receipt receipt = new Receipt();

        receipt.setaPackage(currentPackage);
        receipt.setRecipient(currentUser);
        receipt.setIssuedOn(LocalDate.now());
        BigDecimal fee = BigDecimal.valueOf(aPackage.getWeight()).multiply(MULTIPLICATION_CONSTANT);
        receipt.setFee(fee);

        receiptRepository.save(receipt);
    }

    private boolean arePropertiesInvalid(String username, String packageId) {
        UserServiceModel user = userService.findByUsername(username);
        if (user == null) {
            return true;
        }

        PackageServiceModel aPackage = packageService.findById(packageId);
        return aPackage == null;
    }

    @Override
    public List<ReceiptServiceModel> findAllByUsername(String username) {
        List<ReceiptServiceModel> receipts = receiptRepository.findAllByUsername(username)
                .stream()
                .map(receipt -> modelMapper.map(receipt, ReceiptServiceModel.class))
                .collect(Collectors.toList());
        return receipts;
    }

    @Override
    public ReceiptServiceModel findById(String id) {
        Receipt receipt = receiptRepository.findById(id);
        ReceiptServiceModel receiptServiceModel = modelMapper.map(receipt, ReceiptServiceModel.class);
        return receiptServiceModel;
    }
}
