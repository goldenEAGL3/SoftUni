package softuni.workshop.domain.dtos.jsonDtos.exportDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ProjectJsonDto {

    @Expose
    private String name;

    @Expose
    private String description;

    @Expose
    @SerializedName(value = "is-finished")
    private Boolean isFinished;

    @Expose
    private BigDecimal payment;

    @Expose
    @SerializedName(value = "start-date")
    private String startDate;

    @Expose
    private CompanyJsonDto company;

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

    public CompanyJsonDto getCompany() {
        return this.company;
    }

    public void setCompany(CompanyJsonDto company) {
        this.company = company;
    }
}
