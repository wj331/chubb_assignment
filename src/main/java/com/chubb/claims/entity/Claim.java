package com.chubb.claims.entity;

import com.chubb.claims.enums.ClaimStatus;
import com.chubb.claims.enums.ClaimType;
import com.chubb.claims.enums.Market;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "claims")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claimant_id", nullable = false)
    private Claimant claimant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_officer_id")
    private ClaimsOfficer assignedOfficer;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Market market;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimType claimType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate incidentDate;

    private BigDecimal estimatedLiability;

    private String decisionReason;

    @Version
    private Long version; //prevents two officers silently overwriting

    @CreatedDate
    @Column(nullable = false, updatable=false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}