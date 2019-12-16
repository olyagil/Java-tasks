package com.epam.task2.dao.util;

public enum SearchParameter {

    COUNTRY("name"),
    TOUR_DATE("date"),
    TOUR_DURATION("duration"),
    TOUR_TYPE("tourType"),
    TOUR_MAX_COST("cost"),
    TOUR_MIN_COST("cost"),
    HOTEL_STARS("stars");

    private  Object value;

    SearchParameter(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
