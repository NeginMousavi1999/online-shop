package model.products;

import lombok.Data;
import model.enums.BrandOfDevice;

/**
 * @author Negin Mousavi
 */
@Data
public class ElectronicDevice extends Product {
    private BrandOfDevice brandOfDevice;

    public ElectronicDevice(int count, double cost, BrandOfDevice brandOfDevice) {
        super(count, cost);
        this.brandOfDevice = brandOfDevice;
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                super.toString() +
                ", BrandOfDevice=" + brandOfDevice.toString().toLowerCase() +
                '}';
    }
}
