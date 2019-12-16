package com.epam.task2.entity.enumerution;

public enum TourType {

    SIGHTSEEING,
    SHORE_EXCURSION,
    ADVENTURE,
    SPORTING,
    COMBINING,
    RIVER_CRUISE,
    CYCLE,
    ROMANTIC,
    LUXURY,
    HOLIDAY,
    MINDFULL;


    public Integer getId() {
        return ordinal();
    }

    public static TourType getById(final Integer id) {
        return TourType.values()[id];
    }
}
