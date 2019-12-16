package com.epam.task3.entity.enumerution;

public enum Roles {

    ADMIN("ADMIN"),
    USER("USER");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
