package com.epam.task2.entity;

import com.epam.task2.entity.enumerution.TourType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tour")
public class Tour implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "photo", nullable = false)
    @NotBlank(message = "Path to photo cannot be null")
    private String photo;

    @Column(name = "tour_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "duration", nullable = false)
    @Positive
    private Double duration;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description cannot be null")
    private String description;

    @Column(name = "cost", nullable = false, precision = 9, scale = 3)
    @Positive
    private BigDecimal cost;

    @Column(name = "tour_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TourType tourType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tours")
    @ToString.Exclude
    private Set<User> users;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Review> reviews;
}
