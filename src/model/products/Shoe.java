package model.products;

import lombok.Data;
import model.enums.TypeOfProducts;
import model.enums.TypeOfShoe;

/**
 * @author Negin Mousavi
 */
@Data
public class Shoe extends Product {
    private int sizeOfShoe;
    private String color;
    private TypeOfShoe typeOfShoe;

    public Shoe(int count, double cost, int sizeOfShoe, String color, TypeOfShoe typeOfShoe) {
        super(count, cost);
        this.sizeOfShoe = sizeOfShoe;
        this.color = color;
        this.typeOfShoe = typeOfShoe;
        typeOfProducts = TypeOfProducts.SHOES;
    }

    public Shoe(int id, int count, double cost, int sizeOfShoe, String color, TypeOfShoe typeOfShoe) {
        super(id, count, cost);
        this.sizeOfShoe = sizeOfShoe;
        this.color = color;
        this.typeOfShoe = typeOfShoe;
        typeOfProducts = TypeOfProducts.SHOES;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                super.toString() +
                ", sizeOfShoe=" + sizeOfShoe + ' ' +
                ", color=" + color + ' ' +
                ", typeOfShoe=" + typeOfShoe.toString().toLowerCase() + ' ' +
                '}';
    }
}
