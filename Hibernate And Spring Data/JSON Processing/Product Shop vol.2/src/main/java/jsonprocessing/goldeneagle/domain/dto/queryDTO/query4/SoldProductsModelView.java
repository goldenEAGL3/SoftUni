package jsonprocessing.goldeneagle.domain.dto.queryDTO.query4;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SoldProductsModelView {
    @Expose
    private int count;
    @Expose()
    private List<ProductsModelView> products;

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductsModelView> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductsModelView> products) {
        this.products = products;
    }
}
