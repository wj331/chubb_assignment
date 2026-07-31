package com.chubb.claims.service;

import com.chubb.claims.dto.request.CreateClaimRequest;
import com.chubb.claims.dto.response.ClaimResponse;

public interface ClaimService {

    ClaimResponse createClaim(CreateClaimRequest request);

    ClaimResponse getClaim(Long id);
}
