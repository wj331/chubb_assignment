package com.chubb.claims.entity;

import com.chubb.claims.enums.Market;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "claims_officers")
@Getter
@Setter
public class ClaimsOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Market market;

    private String teamId;
}