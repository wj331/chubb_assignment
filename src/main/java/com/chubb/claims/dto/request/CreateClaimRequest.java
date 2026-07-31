package com.chubb.claims.dto.request;

import com.chubb.claims.enums.ClaimType;
import com.chubb.claims.enums.Market;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClaimRequest {

    @NotNull(message = "Claimant ID is required")
    private Long claimantId;

    @NotNull(message = "Claim type is required")
    private ClaimType claimType;

    @NotNull(message = "Market is required")
    private Market market;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Incident date is required")
    private LocalDate incidentDate;

}