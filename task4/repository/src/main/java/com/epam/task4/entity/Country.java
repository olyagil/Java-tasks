package com.epam.task4.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Document(collection = "countries")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId _id;

    private String name;

    public String get_id() {
        return _id.toHexString();
    }

    public String getName() {
        return name;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

}
