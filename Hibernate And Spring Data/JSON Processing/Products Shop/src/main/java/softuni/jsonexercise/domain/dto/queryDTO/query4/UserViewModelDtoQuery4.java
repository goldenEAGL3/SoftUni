package softuni.jsonexercise.domain.dto.queryDTO.query4;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserViewModelDtoQuery4 implements Serializable {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private SoldProductsModelViewDtoQuery4 soldProducts;

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

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsModelViewDtoQuery4 getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(SoldProductsModelViewDtoQuery4 soldProducts) {
        this.soldProducts = soldProducts;
    }
}
