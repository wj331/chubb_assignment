package com.chubb.claims.dto.request;

import com.chubb.claims.enums.Market;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClaimsOfficerRequest {

    @NotBlank(message = "Officer name is required")
    private String name;

    @NotNull(message = "Market is required")
    private Market market;

    @NotBlank(message = "Team ID is required")
    private String teamId;

}