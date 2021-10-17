package model.products;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class Product {
    protected int count;
    protected double cost;

    public Product(int count, double cost) {
        this.count = count;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "count=" + count + '\n' +
                ", cost=" + cost + '\n';
    }
}
