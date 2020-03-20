package jsonprocessing.goldeneagle.domain.dto.queryDTO.query2;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UsersWithSuccessfullySoldProductsModelView {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductsSoldModelView> soldProducts;

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

    public List<ProductsSoldModelView> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(List<ProductsSoldModelView> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
