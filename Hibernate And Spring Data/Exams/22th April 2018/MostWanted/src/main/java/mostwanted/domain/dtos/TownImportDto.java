package mostwanted.domain.dtos;


import com.google.gson.annotations.Expose;

public class TownImportDto {

    @Expose
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
