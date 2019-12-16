package com.epam.task4.entity;

import com.epam.task4.entity.enumerution.TourType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tours")
public class Tour implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId _id;
    private String photo;
    private Date date;
    private Double duration;
    private String description;
    private BigDecimal cost;
    private TourType tourType;
    private Hotel hotel;
    private Country country;

//    private Set<Users> users;
//    private Set<Review> reviews;


    public String getPhoto() {
        return photo;
    }

    public Date getDate() {
        return date;
    }

    public Double getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public TourType getTourType() {
        return tourType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Country getCountry() {
        return country;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
