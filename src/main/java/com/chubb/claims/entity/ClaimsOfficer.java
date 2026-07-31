package com.chubb.claims.entity;

import com.chubb.claims.enums.Market;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "claims_officers",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "team_id"})
    }
)
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

    @Column(name = "team_id", nullable = false)
    private String teamId;
}