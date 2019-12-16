package com.epam.task4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId _id;
    private Date date;
    private String text;
    private User user;
    private Tour tour;

    public String get_id() {
        return _id.toHexString();
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public Tour getTour() {
        return tour;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }
}
