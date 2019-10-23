package web.beans;

import domain.models.view.ReceiptViewModel;
import org.modelmapper.ModelMapper;
import service.ReceiptService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserReceiptsBean {

    private List<ReceiptViewModel> receiptViewModel;

    private final ReceiptService receiptService;
    private final ModelMapper modelMapper;

    @Inject
    public UserReceiptsBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
        this.initReceipts();
    }

    private void initReceipts() {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        String username = (String) session.getAttribute("username");
        this.receiptViewModel = receiptService.findAllByUsername(username).stream()
                                        .map(receipt -> {
                                            ReceiptViewModel newReceipt = modelMapper.map(receipt, ReceiptViewModel.class);
                                            newReceipt.setRecipient(receipt.getRecipient().getUsername());
                                            return newReceipt;
                                        })
                                            .collect(Collectors.toList());
    }

    public List<ReceiptViewModel> getReceiptViewModel() {
        return this.receiptViewModel;
    }

    public void setReceiptViewModel(List<ReceiptViewModel> receiptViewModel) {
        this.receiptViewModel = receiptViewModel;
    }

    public ReceiptService getReceiptService() {
        return this.receiptService;
    }
}
