package goldeneagle.carsdealer.domain.dto.seedDTOs;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class CustomerSeedDTO {

    @Expose
    private String name;
    @Expose
    private LocalDateTime birthDate;
    @Expose
    private  boolean isYoungDriver;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
