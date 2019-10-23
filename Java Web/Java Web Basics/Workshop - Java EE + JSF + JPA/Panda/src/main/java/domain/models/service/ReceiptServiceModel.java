package domain.models.service;

import domain.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceiptServiceModel {
    String id;
    private BigDecimal fee;
    private LocalDate issuedOn;
    private PackageServiceModel aPackage;
    private UserServiceModel recipient;

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

    public PackageServiceModel getaPackage() {
        return this.aPackage;
    }

    public void setaPackage(PackageServiceModel aPackage) {
        this.aPackage = aPackage;
    }

    public UserServiceModel getRecipient() {
        return this.recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }
}
