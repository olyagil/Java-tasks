package com.epam.task3.entity;


import com.epam.task3.entity.enumerution.Roles;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login")
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    @NotBlank(message = "Login cannot be null")
    private String login;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be null")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_tour",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "tour_id")}
    )
    @ToString.Exclude
    private Set<Tour> tours;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Review> reviews;

 }
