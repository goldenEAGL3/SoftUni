package web.beans;

import domain.models.service.ReceiptServiceModel;
import domain.models.view.ReceiptViewModel;
import org.modelmapper.ModelMapper;
import service.ReceiptService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ReceiptDetailsBean {
    private ReceiptViewModel receiptViewModel;

    private final ReceiptService receiptService;
    private final ModelMapper modelMapper;

    @Inject
    public ReceiptDetailsBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
        this.details();
    }

    private void details() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("receiptId");
        ReceiptServiceModel receipt = receiptService.findById(id);
        receiptViewModel = modelMapper.map(receipt, ReceiptViewModel.class);
        receiptViewModel.setRecipient(receipt.getRecipient().getUsername());
    }

    public ReceiptViewModel getReceiptViewModel() {
        return this.receiptViewModel;
    }

    public void setReceiptViewModel(ReceiptViewModel receiptViewModel) {
        this.receiptViewModel = receiptViewModel;
    }


}
