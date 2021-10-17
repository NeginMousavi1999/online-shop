package model.products;

import lombok.Data;
import model.enums.TypeOfProducts;
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
        typeOfProducts = TypeOfProducts.READABLE_ITEMS;
    }

    public ReadableItem(int id, int count, double cost, int countOfPages, TypeOfReadableItem typeOfReadableItem) {
        super(id, count, cost);
        this.countOfPages = countOfPages;
        this.typeOfReadableItem = typeOfReadableItem;
        typeOfProducts = TypeOfProducts.READABLE_ITEMS;
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
