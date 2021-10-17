package model;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class Address {
    private int id;
    private String postalCode;

    public Address(String postalCode) {
        this.postalCode = postalCode;
    }
}
