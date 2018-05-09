package org.myplanettoo.noplastic;

public class NotificationItem {

    public static final String MORNING_TYPE = "morning";
    public static final String EVENING_TYPE = "evening";

    private String title = "";
    private String description = "";
    private String type = "";

    public static NotificationItem create() {
        return new NotificationItem();
    }

    public String getTitle() {
        return title;
    }

    public NotificationItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NotificationItem setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getType() {
        return type;
    }

    public NotificationItem setType(String type) {
        this.type = type;
        return this;
    }
}
