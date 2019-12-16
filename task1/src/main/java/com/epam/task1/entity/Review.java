package com.epam.task1.entity;

import java.sql.Date;

public class Review extends Entity {

    private Date date;
    private String text;
    private User user;
    private Tour tour;

    public Review() {
    }

    public Review(final Date date, final String text, final User user,
                  final Tour tour) {
        this.date = date;
        this.text = text;
        this.user = user;
        this.tour = tour;
    }

    public Review(final Long id, final Date date, final String text,
                  final User user, final Tour tour) {
        super(id);
        this.date = date;
        this.text = text;
        this.user = user;
        this.tour = tour;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(final Tour tour) {
        this.tour = tour;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Review review = (Review) o;

        if (date != null ? !date.equals(review.date) : review.date != null)
            return false;
        if (text != null ? !text.equals(review.text) : review.text != null)
            return false;
        if (user != null ? !user.equals(review.user) : review.user != null)
            return false;
        return tour != null ? tour.equals(review.tour) : review.tour == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nReview{" + super.toString()
                + "date=" + date
                + ", text='" + text + '\''
                + ", user=" + user
                + ", tour=" + tour
                + "} ";
    }
}
