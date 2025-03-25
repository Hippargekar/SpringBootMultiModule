package org.root.locator;

public enum NotificationTypes {
    EMAIL(Types.EMAIL,"emailType"),
    SMS(Types.SMS, "smsType");

    private final String type;
    private final String value;
    NotificationTypes(String type, String value) {
        this.type = type;
        this.value = value;
    }

    // Getter method to retrieve the type
    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public interface Types {
        String EMAIL= "EMAIL";
        String SMS= "SMS";
    }
}
