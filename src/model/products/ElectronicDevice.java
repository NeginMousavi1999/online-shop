package model.products;

import lombok.Data;
import model.enums.BrandOfDevice;

/**
 * @author Negin Mousavi
 */
@Data
public class ElectronicDevice extends Product{
    private BrandOfDevice brandOfDevice;

    public ElectronicDevice(int count, double cost, BrandOfDevice brandOfDevice) {
        super(count, cost);
        this.brandOfDevice = brandOfDevice;
    }
}
