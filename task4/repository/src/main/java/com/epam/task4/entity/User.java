package com.epam.task4.entity;


import com.epam.task4.entity.enumerution.Roles;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private ObjectId _id;
    private String login;
    private String password;
    private Roles role;

    public User() {
    }
//    private Set<Tour> tours;
//    private Set<Review> reviews;

    public String get_id() {
        return _id.toHexString();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public User(ObjectId _id, String login, String password, Roles role) {
        this._id = _id;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
