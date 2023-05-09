package org.googlemap.infra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "donut")
public class Donut {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @Column(nullable = false)
    private boolean eatInOk = true;

    @Column(nullable = false)
    private boolean takeOutOk = false;
}
