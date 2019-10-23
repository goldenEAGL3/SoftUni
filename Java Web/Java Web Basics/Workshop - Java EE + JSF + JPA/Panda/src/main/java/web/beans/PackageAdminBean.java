package web.beans;

import domain.entity.enums.Status;
import domain.models.view.PackageViewModel;
import org.modelmapper.ModelMapper;
import service.PackageService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackageAdminBean {

    private List<PackageViewModel> pendingPackageViewModels;
    private List<PackageViewModel> shippedPackageViewModels;
    private List<PackageViewModel> deliveredPackageViewModels;

    private final PackageService packageService;
    private final ModelMapper modelMapper;


    @Inject
    public PackageAdminBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.initPackages();
    }




    public void shipPackage(String id) throws IOException {
        this.packageService.changeStatus(id);
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/faces/pending.xhtml");
    }

    public void deliverPackage(String id) throws IOException {
        packageService.changeStatus(id);
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/faces/shipped.xhtml");
    }

    private void initPackages() {
        this.pendingPackageViewModels = this.collectPackages(Status.Pending);
        this.shippedPackageViewModels = this.collectPackages(Status.Shipped);
        this.deliveredPackageViewModels = this.collectPackages(Status.Delivered);
    }

    private List<PackageViewModel> collectPackages(Status status) {
        return this.packageService.findAllByStatus(status)
                .stream().map(pack -> {
                    PackageViewModel newPackage = modelMapper.map(pack, PackageViewModel.class);
                    newPackage.setRecipient(pack.getRecipient().getUsername());
                    return newPackage;
                }).collect(Collectors.toList());
    }

    public List<PackageViewModel> getPendingPackageViewModels() {
        return this.pendingPackageViewModels;
    }

    public void setPendingPackageViewModels(List<PackageViewModel> pendingPackageViewModels) {
        this.pendingPackageViewModels = pendingPackageViewModels;
    }

    public List<PackageViewModel> getShippedPackageViewModels() {
        return this.shippedPackageViewModels;
    }

    public void setShippedPackageViewModels(List<PackageViewModel> shippedPackageViewModels) {
        this.shippedPackageViewModels = shippedPackageViewModels;
    }

    public List<PackageViewModel> getDeliveredPackageViewModels() {
        return this.deliveredPackageViewModels;
    }

    public void setDeliveredPackageViewModels(List<PackageViewModel> deliveredPackageViewModels) {
        this.deliveredPackageViewModels = deliveredPackageViewModels;
    }

}
