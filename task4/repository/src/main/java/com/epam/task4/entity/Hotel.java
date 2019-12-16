package com.epam.task4.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "hotels")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId _id;
    private String name;
    private Integer stars;
    private String website;
    private String latitude;
    private String longitude;


//    private Set<Feature> features;
//    private Set<Tour> tours;

    public String get_id() {
        return _id.toHexString();
    }

    public String getName() {
        return name;
    }

    public Integer getStars() {
        return stars;
    }

    public String getWebsite() {
        return website;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
