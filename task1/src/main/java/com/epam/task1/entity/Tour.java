package com.epam.task1.entity;

import com.epam.task1.entity.enumerution.TourType;

import java.sql.Date;

public class Tour extends Entity {

    private String photo;
    private Date date;
    private Double duration;
    private String description;
    private Double cost;
    private TourType tourType;
    private Hotel hotel;
    private Country country;

    public Tour() {
    }

    public Tour(final String photo, final Date date, final Double duration,
                final String description, final Double cost,
                final TourType tourType, final Hotel hotel,
                final Country country) {
        this.photo = photo;
        this.date = date;
        this.duration = duration;
        this.description = description;
        this.cost = cost;
        this.tourType = tourType;
        this.hotel = hotel;
        this.country = country;
    }

    public Tour(final Long id, final String photo, final Date date,
                final Double duration, final String description,
                final Double cost, final TourType tourType,
                final Hotel hotel, final Country country) {
        super(id);
        this.photo = photo;
        this.date = date;
        this.duration = duration;
        this.description = description;
        this.cost = cost;
        this.tourType = tourType;
        this.hotel = hotel;
        this.country = country;
    }

    public Tour(final Long id) {
        super(id);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(final Double duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(final TourType tourType) {
        this.tourType = tourType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(final Hotel hotel) {
        this.hotel = hotel;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Tour tour = (Tour) o;

        if (photo != null ? !photo.equals(tour.photo) : tour.photo != null)
            return false;
        if (date != null ? !date.equals(tour.date) : tour.date != null)
            return false;
        if (duration != null ? !duration.equals(tour.duration) : tour.duration != null)
            return false;
        if (description != null ? !description.equals(tour.description) : tour.description != null)
            return false;
        if (cost != null ? !cost.equals(tour.cost) : tour.cost != null)
            return false;
        if (tourType != tour.tourType) return false;
        if (hotel != null ? !hotel.equals(tour.hotel) : tour.hotel != null)
            return false;
        return country != null ? country.equals(tour.country) : tour.country == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (tourType != null ? tourType.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nTour{" + super.toString()
                + "photo='" + photo + '\''
                + ", date=" + date
                + ", duration=" + duration
                + ", description='" + description + '\''
                + ", cost=" + cost
                + ", tourType=" + tourType
                + ", hotel=" + hotel
                + ", country=" + country
                + "} ";
    }
}
