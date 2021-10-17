package model.products;

import lombok.Data;
import model.enums.TypeOfReadableItem;

/**
 * @author Negin Mousavi
 */
@Data
public class ReadableItem extends Product {
    private int countOfPages;
    private TypeOfReadableItem typeOfReadableItem;

    public ReadableItem(int count, double cost, int countOfPages, TypeOfReadableItem typeOfReadableItem) {
        super(count, cost);
        this.countOfPages = countOfPages;
        this.typeOfReadableItem = typeOfReadableItem;
    }

    @Override
    public String toString() {
        return "ReadableItem{" +
                super.toString() +
                ", BrandOfDevice=" + countOfPages + ' ' +
                ", typeOfReadableItem=" + typeOfReadableItem.toString().toLowerCase() +
                '}';
    }
}
