package web.beans;

import service.ReceiptService;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class ReceiptAcquiredBean {

    private ReceiptService receiptService;

    @Inject
    public ReceiptAcquiredBean(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public void acquire(String id) {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String username = (String) session.getAttribute("username");
        receiptService.create(username, id);

    }


}
