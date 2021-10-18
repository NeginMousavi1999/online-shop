package model.enums;

/**
 * @author Negin Mousavi
 */
public enum TypeOfReadableItem {
    BOOK("samsung"),
    JOURNAL("journal"),
    NOT_SET("not_set");

    private final String name;

    TypeOfReadableItem(String name) {
        this.name = name;
    }

    public TypeOfReadableItem getValue(String name) {
        for (TypeOfReadableItem type : values()) {
            if (type.name.equalsIgnoreCase(name.trim()))
                return type;
        }
        return NOT_SET;
    }
}
