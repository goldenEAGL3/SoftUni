package softuni.jsonexercise.domain.dto.seedDTO;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class UserSeedDTO implements Serializable {

    @Expose
    private String firstName;

    @Expose
    @NotNull
    @Size(min = 3, message = "Last name should be at least 3 symbols long!")
    private String lastName;

    @Expose
    @Min(value = 0, message = "Age should not be negative number!")
    private int age;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
