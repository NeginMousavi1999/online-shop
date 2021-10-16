package model;

import lombok.Data;
import model.products.Product;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private List<Product> products;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
