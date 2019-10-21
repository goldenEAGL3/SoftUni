package service;

import domain.entity.Package;
import domain.entity.User;
import domain.entity.enums.Status;
import domain.models.service.PackageServiceModel;
import org.modelmapper.ModelMapper;
import repository.PackageRepository;
import repository.UserRepository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(PackageServiceModel packageServiceModel) {
        User user = this.userRepository.findByUsername(packageServiceModel.getRecipient().getUsername());
//        if(user == null) {
//
//        }
        Package newPackage = modelMapper.map(packageServiceModel, Package.class);
        newPackage.setRecipient(user);
        packageRepository.save(newPackage);
    }

    @Override
    public List<PackageServiceModel> findAllByStatus(Status status) {
        List<PackageServiceModel> packages = packageRepository.findAllByStatus(status)
                .stream()
                .map(pack -> modelMapper.map(pack, PackageServiceModel.class))
                .collect(Collectors.toList());
        return packages;
    }

    @Override
    public void changeStatus(String id) {
        Package newPackage = packageRepository.findById(id);

        Status[] values = Status.values();
        Status oldStatus = newPackage.getStatus();
        newPackage.setStatus(values[oldStatus.ordinal() + 1]);

        if(newPackage.getEstimatedDeliveryDate() == null) {
            LocalDate date = changeDateOfPackage();
            newPackage.setEstimatedDeliveryDate(date);
        }
        packageRepository.update(newPackage);
    }

    @Override
    public PackageServiceModel findById(String id) {
         Package desiredPackage = packageRepository.findById(id);
        PackageServiceModel packageServiceModel = modelMapper.map(desiredPackage, PackageServiceModel.class);
        return packageServiceModel;
    }

    @Override
    public List<PackageServiceModel> findByUsername(String username) {
        List<PackageServiceModel> packages = packageRepository.findAllByUsername(username)
                .stream().map(pack -> modelMapper.map(pack, PackageServiceModel.class))
                .collect(Collectors.toList());
        return packages;
    }



    private LocalDate changeDateOfPackage() {
        LocalDate date = LocalDate.now();
        Random random = new Random();
        long randomNumberOfDays = random.nextInt(21) + 20;
        date = date.plusDays(randomNumberOfDays);
        return date;
    }
}
