package model;

import lombok.Data;
import model.enums.CartStatus;
import model.products.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class Cart {
    private int id;
    private List<Product> products;
    private CartStatus cartStatus;


    public Cart(List<Product> products, CartStatus cartStatus) {
        this.products = products;
        this.cartStatus = cartStatus;
    }

    public Cart(int id, List<Product> products, CartStatus cartStatus) {
        this.id = id;
        this.products = products;
        this.cartStatus = cartStatus;
    }
}
