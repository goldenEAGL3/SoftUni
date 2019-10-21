package domain.models.view;


import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceiptViewModel {

    private String id;
    private BigDecimal fee;
    private LocalDate issuedOn;
    private PackageViewModel aPackage;
    private String recipient;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDate getIssuedOn() {
        return this.issuedOn;
    }

    public void setIssuedOn(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }

    public PackageViewModel getaPackage() {
        return this.aPackage;
    }

    public void setaPackage(PackageViewModel aPackage) {
        this.aPackage = aPackage;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
