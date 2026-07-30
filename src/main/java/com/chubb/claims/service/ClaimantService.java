package com.chubb.claims.service;

import com.chubb.claims.dto.request.CreateClaimantRequest;
import com.chubb.claims.dto.response.ClaimantResponse;

public interface ClaimantService {

    ClaimantResponse createClaimant(CreateClaimantRequest request);

    ClaimantResponse getClaimant(Long id);

}