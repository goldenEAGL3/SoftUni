package hiberspring.domain.dtos.importJSON;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownDto {
    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private Integer population;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
