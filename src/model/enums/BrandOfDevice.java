package model.enums;

/**
 * @author Negin Mousavi
 */
public enum BrandOfDevice {
    SAMSUNG("samsung"),
    LG("lg"),
    SONY("sony"),
    NOT_SET("not_set");

    private final String name;

    BrandOfDevice(String name) {
        this.name = name;
    }

    public BrandOfDevice getValue(String name) {
        for (BrandOfDevice brand : values()) {
            if (brand.name.equalsIgnoreCase(name.trim()))
                return brand;
        }
        return NOT_SET;
    }
}
