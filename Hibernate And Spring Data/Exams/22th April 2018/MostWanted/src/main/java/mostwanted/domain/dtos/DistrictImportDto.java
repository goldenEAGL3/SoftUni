package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

public class DistrictImportDto {

    @Expose
    private String name;

    @Expose
    private String townName;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
