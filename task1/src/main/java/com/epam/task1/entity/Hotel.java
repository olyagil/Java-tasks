package com.epam.task1.entity;

import com.epam.task1.entity.enumerution.Feature;

public class Hotel extends Entity {

    private String name;
    private Integer stars;
    private String website;
    private String latitude;
    private String longitude;
    private Feature feature;

    public Hotel() {
    }

    public Hotel(final Long id) {
        super(id);
    }

    public Hotel(final String name, final Integer stars, final String website,
                 final String latitude,
                 final String longitude, final Feature feature) {
        this.name = name;
        this.stars = stars;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
        this.feature = feature;
    }

    public Hotel(final Long id, final String name, final Integer stars,
                 final String website, final String latitude,
                 final String longitude, final Feature feature) {
        super(id);
        this.name = name;
        this.stars = stars;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(final Integer stars) {
        this.stars = stars;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(final String website) {
        this.website = website;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(final Feature feature) {
        this.feature = feature;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Hotel hotel = (Hotel) o;

        if (name != null ? !name.equals(hotel.name) : hotel.name != null)
            return false;
        if (stars != null ? !stars.equals(hotel.stars) : hotel.stars != null)
            return false;
        if (website != null ? !website.equals(hotel.website) : hotel.website != null)
            return false;
        if (latitude != null ? !latitude.equals(hotel.latitude) : hotel.latitude != null)
            return false;
        if (longitude != null ? !longitude.equals(hotel.longitude) : hotel.longitude != null)
            return false;
        return feature == hotel.feature;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (stars != null ? stars.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (feature != null ? feature.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" + super.toString()
                + "name='" + name + '\''
                + ", stars=" + stars
                + ", website='" + website + '\''
                + ", latitude='" + latitude + '\''
                + ", longitude='" + longitude + '\''
                + ", feature=" + feature + "} ";
    }
}
