package jsonprocessing.goldeneagle.domain.dto.queryDTO.query4;

import com.google.gson.annotations.Expose;

public class UsersWithAtLeastOneProductSoldModelView {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private SoldProductsModelView soldProducts;

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

    public SoldProductsModelView getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(SoldProductsModelView soldProducts) {
        this.soldProducts = soldProducts;
    }
}
