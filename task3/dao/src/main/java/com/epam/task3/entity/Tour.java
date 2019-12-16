package com.epam.task3.entity;

import com.epam.task3.entity.enumerution.TourType;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String photo;

    @Column(name = "tour_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "duration", nullable = false)
    private Double duration;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description cannot be null")
    private String description;

    @Column(name = "cost", nullable = false, precision = 9, scale = 3)
    private BigDecimal cost;

    @Column(name = "tour_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TourType tourType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tours")
    @ToString.Exclude
    private Set<User> users;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Review> reviews;
}
