package softuni.workshop.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity{
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 20, message = "Project name should be between 3 and 20 symbols long.")
    private String name;

    @Column(name = "description", nullable = false)
    @Size(min = 3, max = 1000, message = "Description should be between 10 and 20 symbols long.")
    private String description;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "payment", nullable = false)
    @Min(value = 0, message = "Payment can't be negative value.")
    private BigDecimal payment;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "project")
    private List<Employee> employees;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFinished() {
        return this.isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public BigDecimal getPayment() {
        return this.payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
