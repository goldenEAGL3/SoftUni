package domain.entity;

import domain.entity.base.BaseEntity;
import domain.entity.enums.Sector;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @Column(nullable = false)
    private String profession;

    @Column(nullable = false)

    @DecimalMin("0.01")
    private BigDecimal salary;

    @Column(nullable = false)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Sector getSector() {
        return this.sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
