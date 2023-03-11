package org.googlemap.infra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Integer userId;

    @Column(nullable = false)
    private String userName;
}
