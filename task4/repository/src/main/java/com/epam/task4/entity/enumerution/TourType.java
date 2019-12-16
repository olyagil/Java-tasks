package com.epam.task4.entity.enumerution;

public enum TourType {

    EXCURSION("Excursion"),
    ADVENTURE("Adventure"),
    SPORTING("Sporting"),
    COMBINING("Combinig"),
    RIVER_CRUISE("River Cruise"),
    CYCLE("Cycle"),
    ROMANTIC("Romantic"),
    LUXURY("Luxury"),
    HOLIDAY("Holiday"),
    MINDFULL("Mindfull");

    private String value;

    TourType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
