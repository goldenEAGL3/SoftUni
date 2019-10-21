package web.beans;

import domain.entity.enums.Status;
import domain.models.binding.PackageCreateBindingModel;
import domain.models.service.PackageServiceModel;
import domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import service.PackageService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class PackageCreateBean {

    private PackageCreateBindingModel packageCreateBindingModel;

    private  final PackageService packageService;
    private  final ModelMapper modelMapper;

    @Inject
    public PackageCreateBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.packageCreateBindingModel = new PackageCreateBindingModel();
    }

    public PackageCreateBindingModel getPackageCreateBindingModel() {
        return this.packageCreateBindingModel;
    }

    public void setPackageCreateBindingModel(PackageCreateBindingModel packageCreateBindingModel) {
        this.packageCreateBindingModel = packageCreateBindingModel;
    }

    public void create() throws IOException {
        PackageServiceModel packageServiceModel = modelMapper.map(packageCreateBindingModel, PackageServiceModel.class);
        packageServiceModel.setStatus(Status.Pending);
        packageServiceModel.setRecipient(new UserServiceModel());
        packageServiceModel.getRecipient().setUsername(packageCreateBindingModel.getRecipient());
        packageService.create(packageServiceModel);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/home.xhtml");
    }

}
