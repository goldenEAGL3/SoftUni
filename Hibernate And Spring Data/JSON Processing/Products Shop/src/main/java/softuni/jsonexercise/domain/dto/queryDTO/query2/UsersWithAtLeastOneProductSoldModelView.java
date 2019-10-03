package softuni.jsonexercise.domain.dto.queryDTO.query2;

import com.google.gson.annotations.Expose;


import java.io.Serializable;
import java.util.Set;

public class UsersWithAtLeastOneProductSoldModelView implements Serializable {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Set<SoldProductsModelView> soldProducts;

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

    public Set<SoldProductsModelView> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<SoldProductsModelView> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
