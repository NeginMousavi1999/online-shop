package model.enums;

/**
 * @author Negin Mousavi
 */
public enum TypeOfProducts {
    ELECTRONIC_DEVICES("electronic_devices"),
    SHOES("shoes"),
    READABLE_ITEMS("readable_items"),
    NOT_SET("not_set");

    private final String name;

    TypeOfProducts(String name) {
        this.name = name;
    }

    public TypeOfProducts getValue(String name) {
        for (TypeOfProducts type : values()) {
            if (type.name.equalsIgnoreCase(name.trim()))
                return type;
        }
        return NOT_SET;
    }
}
