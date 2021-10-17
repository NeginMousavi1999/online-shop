package model.products;

import lombok.Data;
import model.enums.TypeOfShoe;

/**
 * @author Negin Mousavi
 */
@Data
public class Shoe extends Product{
    private int sizeOfShoe;
    private String color;
    private TypeOfShoe typeOfShoe;

    public Shoe(int count, double cost, int sizeOfShoe, String color, TypeOfShoe typeOfShoe) {
        super(count, cost);
        this.sizeOfShoe = sizeOfShoe;
        this.color = color;
        this.typeOfShoe = typeOfShoe;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                super.toString() +
                ", sizeOfShoe=" + sizeOfShoe + '\n' +
                ", color=" + color + '\n' +
                ", typeOfShoe=" + typeOfShoe.toString().toLowerCase() + '\n' +
                '}';
    }
}
