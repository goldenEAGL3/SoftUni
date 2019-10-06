package alararestaurant.domain.dtos.importJSON;

import com.google.gson.annotations.Expose;
import javax.validation.constraints.*;

public class EmployeeDto {

    @Expose
    @Size(min = 3, max = 30)
    private String name;

    @Expose
    @Min(15)
    @Max(80)
    private Integer age;

    @Expose
    @Size(min = 3, max = 30)
    private String position;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
