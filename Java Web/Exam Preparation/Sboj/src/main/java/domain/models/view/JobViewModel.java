package domain.models.view;

import domain.entity.enums.Sector;

import java.math.BigDecimal;

public class JobViewModel {

    private String id;
    private Sector sector;
    private String profession;
    private BigDecimal salary;
    private String description;
    private String imgResource;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getImgResource() {
        return this.imgResource;
    }

    public void setImgResource(String imgResource) {
        this.imgResource = imgResource;
    }
}
