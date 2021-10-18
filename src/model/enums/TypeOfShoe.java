package model.enums;

/**
 * @author Negin Mousavi
 */
public enum TypeOfShoe {
    SPORT("sport"),
    FORMAL("formal"),
    NOT_SET("not_set");

    private final String name;

    TypeOfShoe(String name) {
        this.name = name;
    }

    public TypeOfShoe getValue(String name) {
        for (TypeOfShoe type : values()) {
            if (type.name.equalsIgnoreCase(name.trim()))
                return type;
        }
        return NOT_SET;
    }
}
