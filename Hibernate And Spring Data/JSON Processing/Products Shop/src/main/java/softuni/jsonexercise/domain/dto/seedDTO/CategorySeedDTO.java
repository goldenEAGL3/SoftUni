package softuni.jsonexercise.domain.dto.seedDTO;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategorySeedDTO implements Serializable {
    @Expose
    @Size(min = 3, max = 15, message = "Category name must be between 3 and 15 symbols long!")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
