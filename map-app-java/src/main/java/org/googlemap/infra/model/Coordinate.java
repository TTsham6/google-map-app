package org.googlemap.infra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "coordinate")
public class Coordinate {

    @Id
    @GeneratedValue
    private Long coordinateId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lng;
}
