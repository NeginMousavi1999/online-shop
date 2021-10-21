package model.person;

import lombok.Data;
import model.ProductSold;
import model.enums.UserRole;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private List<ProductSold> productSold;
    private Address address;
    private UserRole userRole;


    public User(String username, String password, Address address, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.userRole = userRole;
    }

    public User(int id, String username, String password, Address address, UserRole userRole) {
        this(username, password, address, userRole);
        this.id = id;
    }
}
