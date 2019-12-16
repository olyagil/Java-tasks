package com.epam.task1.entity;

public class Country extends Entity {

    private String name;

    public Country() {
    }

    public Country(final String name) {
        this.name = name;
    }

    public Country(Long id) {
        super(id);
    }

    public Country(final Long id, final String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Country country = (Country) o;

        return name != null ? name.equals(country.name) : country.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nCountry{" + super.toString()
                + " name='" + name + '\''
                + "} ";
    }
}
