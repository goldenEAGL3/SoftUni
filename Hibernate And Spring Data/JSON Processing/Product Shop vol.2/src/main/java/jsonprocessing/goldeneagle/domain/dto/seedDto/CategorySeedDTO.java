package jsonprocessing.goldeneagle.domain.dto.seedDto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CategorySeedDTO {
    @Expose
    @Size(min = 3, max = 15, message = "Category name should be between 3 and 15 symbols long!")

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
