package web.beans;

import domain.entity.enums.Status;
import domain.models.service.PackageServiceModel;
import domain.models.view.PackageViewModel;
import org.modelmapper.ModelMapper;
import service.PackageService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserHomeBean {
    private List<PackageViewModel> pendingPackages;
    private List<PackageViewModel> shippedPackages;
    private List<PackageViewModel> deliveredPackages;

    private  final PackageService packageService;
    private  final ModelMapper modelMapper;



    @Inject
    public UserHomeBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.initPackages();

    }

    private void initPackages() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String username = (String) session.getAttribute("username");

        List<PackageServiceModel> userPackages = this.packageService.findByUsername(username);
        this.distributePackages(userPackages);
    }

    private void distributePackages(List<PackageServiceModel> userPackages) {
        this.pendingPackages = filterPackages(userPackages, Status.Pending);
        this.shippedPackages = filterPackages(userPackages, Status.Shipped);
        this.deliveredPackages = filterPackages(userPackages, Status.Delivered);
    }

    private List<PackageViewModel> filterPackages(List<PackageServiceModel> userPackages, Status filter) {
        return userPackages
                .stream()
                .filter(pack -> pack.getStatus().equals(filter))
                .map(pack -> {
                    PackageViewModel newPackage = modelMapper.map(pack, PackageViewModel.class);
                    newPackage.setRecipient(pack.getRecipient().getUsername());
                    return newPackage;
                })
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPendingPackages() {
        return this.pendingPackages;
    }

    public void setPendingPackages(List<PackageViewModel> pendingPackages) {
        this.pendingPackages = pendingPackages;
    }

    public List<PackageViewModel> getShippedPackages() {
        return this.shippedPackages;
    }

    public void setShippedPackages(List<PackageViewModel> shippedPackages) {
        this.shippedPackages = shippedPackages;
    }

    public List<PackageViewModel> getDeliveredPackages() {
        return this.deliveredPackages;
    }

    public void setDeliveredPackages(List<PackageViewModel> deliveredPackages) {
        this.deliveredPackages = deliveredPackages;
    }
}
