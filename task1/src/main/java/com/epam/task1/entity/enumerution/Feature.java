package com.epam.task1.entity.enumerution;

public enum Feature {

    WIFI,
    WORK_DESK,
    OUTLETS_GALORE,
    BUSINESS_CENTRE,
    FITNESS_CENTRE,
    CONCIERGE_SERVICE,
    COMMUNAL_SPACE,
    EXPRESS_CHECK_IN,
    LAUNDRY,
    ROOM_SERVICE,
    ANIMATION;


    public Integer getId() {
        return ordinal();
    }

    public static Feature getById(Integer id) {
        return Feature.values()[id];
    }
}
