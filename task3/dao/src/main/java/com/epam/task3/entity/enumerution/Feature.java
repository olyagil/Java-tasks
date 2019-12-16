package com.epam.task3.entity.enumerution;

public enum Feature {

    WIFI("WiFi"),
    WORK_DESK("Work Desk"),
    OUTLETS_GALORE("Outlets Galore"),
    BUSINESS_CENTRE("Business Centre"),
    FITNESS_CENTRE("Fitness Centre"),
    CONCIERGE_SERVICE("Concierge Service"),
    COMMUNAL_SPACE("Communal Space"),
    EXPRESS_CHECK_IN("Express Check in"),
    LAUNDRY("Laundry"),
    ROOM_SERVICE("Room Service"),
    ANIMATION("Animation");

    private String value;

    Feature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
