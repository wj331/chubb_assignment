package com.chubb.claims.dto.response;

import com.chubb.claims.enums.Market;

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
public class ClaimsOfficerResponse {

    private Long id;

    private String name;

    private Market market;

    private String teamId;

}