package domain.entity;

import domain.entity.base.BaseEntity;
import domain.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "packages")
public class Package extends BaseEntity {

    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Double weight;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;

    @ManyToOne()
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @OneToMany(mappedBy = "aPackage")
    private List<Receipt> receipts;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return this.estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public User getRecipient() {
        return this.recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public List<Receipt> getReceipts() {
        return this.receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
