package com.chubb.claims.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimantResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;
}