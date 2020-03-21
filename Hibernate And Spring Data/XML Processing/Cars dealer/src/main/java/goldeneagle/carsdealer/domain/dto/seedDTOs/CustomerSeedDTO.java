package goldeneagle.carsdealer.domain.dto.seedDTOs;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDTO {

    @XmlAttribute
    @NotNull
    private String name;
    @XmlElement(name = "birth-date")
    @NotNull
    private LocalDateTime birthDate;
    @XmlElement(name = "is-young-driver")
    @NotNull
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
