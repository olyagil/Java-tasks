package com.epam.task2.entity;

import com.epam.task2.entity.enumerution.Feature;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "hotel_name", nullable = false)
    @NotBlank(message = "Name cannot be null")
    private String name;

    @Column(name = "stars", nullable = false)
    @Max(value = 10, message = "Stars cannot be less then 0 or greater then 10")
    private Integer stars;

    @Column(name = "website", nullable = false)
    @NotBlank(message = "Website cannot be null")
    private String website;

    @Column(name = "latitude", nullable = false)
    @NotBlank(message = "Latitude cannot be null")
    private String latitude;

    @Column(name = "longitude", nullable = false)
    @NotBlank(message = "Longitude cannot be null")
    private String longitude;

    @Column(name = "features")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Feature.class)
    @ToString.Exclude
    private List<Feature> features;

    @OneToMany(mappedBy = "hotel")
    @ToString.Exclude
    private Set<Tour> tours;

}
