package model.enums;

/**
 * @author Negin Mousavi
 */
public enum CartStatus {
    COMPLETED("completed"),
    NOT_COMPLETED("not_completed"),
    NOT_SET("not_set");

    private final String name;

    CartStatus(String name) {
        this.name = name;
    }

    public CartStatus getValue(String name) {
        for (CartStatus status : values()) {
            if (status.name.equalsIgnoreCase(name.trim()))
                return status;
        }
        return NOT_SET;
    }
}
