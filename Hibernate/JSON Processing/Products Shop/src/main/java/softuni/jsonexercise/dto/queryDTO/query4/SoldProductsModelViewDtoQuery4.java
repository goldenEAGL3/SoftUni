package softuni.jsonexercise.dto.queryDTO.query4;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SoldProductsModelViewDtoQuery4 {

    @Expose
    private Integer count;
    @Expose
    private List<ProductModelViewDtoQuery4> products;

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductModelViewDtoQuery4> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductModelViewDtoQuery4> products) {
        this.products = products;
    }
}
