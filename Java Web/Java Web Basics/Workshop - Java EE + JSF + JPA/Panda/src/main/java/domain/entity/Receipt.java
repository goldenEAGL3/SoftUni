package domain.entity;

import domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "recepits")
public class Receipt extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal fee;

    @Column(name = "issued_on",nullable = false)
    private LocalDate issuedOn;

    @ManyToOne()
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne()
    @JoinColumn(name = "package_id")
    private Package aPackage;

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

    public User getRecipient() {
        return this.recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Package getaPackage() {
        return this.aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
