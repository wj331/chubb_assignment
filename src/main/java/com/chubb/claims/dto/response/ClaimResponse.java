package com.chubb.claims.dto.response;

import com.chubb.claims.enums.ClaimStatus;
import com.chubb.claims.enums.ClaimType;
import com.chubb.claims.enums.Market;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimResponse {

    private Long id;

    private String claimNumber;

    private String claimantName;

    private String assignedOfficerName;

    private Market market;

    private ClaimStatus status;

    private ClaimType claimType;

    private String description;

    private LocalDate incidentDate;

    private BigDecimal estimatedLiability;

    private String decisionReason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}