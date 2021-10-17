package model.products;

import lombok.Data;
import model.enums.BrandOfDevice;
import model.enums.TypeOfProducts;

/**
 * @author Negin Mousavi
 */
@Data
public class ElectronicDevice extends Product {
    private BrandOfDevice brandOfDevice;

    public ElectronicDevice(int count, double cost, BrandOfDevice brandOfDevice) {
        super(count, cost);
        this.brandOfDevice = brandOfDevice;
        typeOfProducts = TypeOfProducts.ELECTRONIC_DEVICES;
    }

    public ElectronicDevice(int id, int count, double cost, BrandOfDevice brandOfDevice) {
        super(id, count, cost);
        this.brandOfDevice = brandOfDevice;
        typeOfProducts = TypeOfProducts.ELECTRONIC_DEVICES;
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                super.toString() +
                ", BrandOfDevice=" + brandOfDevice.toString().toLowerCase() +
                '}';
    }
}
