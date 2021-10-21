package model;

import lombok.Data;
import model.enums.CartStatus;
import model.products.Product;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class ProductSold {
    private int id;
    private List<Product> products;
    private CartStatus cartStatus;

    public ProductSold(int id, List<Product> products, CartStatus cartStatus) {
        this.id = id;
        this.products = products;
        this.cartStatus = cartStatus;
    }
}
