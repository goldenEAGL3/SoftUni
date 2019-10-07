package hiberspring.domain.dtos.importJSON;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class BranchDto {
    @Expose
    @NotNull
    private String name;
    @Expose
    @NotNull
    private String town;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
