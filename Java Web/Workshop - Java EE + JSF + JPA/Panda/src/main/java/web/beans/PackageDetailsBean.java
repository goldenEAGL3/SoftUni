package web.beans;

import domain.models.service.PackageServiceModel;
import domain.models.view.PackageViewModel;
import org.modelmapper.ModelMapper;
import service.PackageService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class PackageDetailsBean {

    private PackageViewModel packageDetail;

    private final PackageService packageService;
    private final ModelMapper modelMapper;

    @Inject
    public PackageDetailsBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
        this.details();
    }


    public void details() {
//        try {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("packageId");
        PackageServiceModel packageServiceModel = packageService.findById(id);
        this.packageDetail = modelMapper.map(packageServiceModel, PackageViewModel.class);
        this.packageDetail.setRecipient(packageServiceModel.getRecipient().getUsername());
        //        } catch (IllegalArgumentException e) {
//
//        }

    }

    public PackageViewModel getPackageDetail() {
        return this.packageDetail;
    }

    public void setPackageDetail(PackageViewModel packageDetail) {
        this.packageDetail = packageDetail;
    }
}
