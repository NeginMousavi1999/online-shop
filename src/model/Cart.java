package model;

import lombok.Data;
import model.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class Cart {
    private int id;
    private List<Product> products = new ArrayList<>();

    public Cart(List<Product> products) {
        this.products = products;
    }
}
