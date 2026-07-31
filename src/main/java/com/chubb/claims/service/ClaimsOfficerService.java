package com.chubb.claims.service;

import java.util.List;

import com.chubb.claims.dto.request.CreateClaimsOfficerRequest;
import com.chubb.claims.dto.response.ClaimsOfficerResponse;

public interface ClaimsOfficerService {

    ClaimsOfficerResponse createOfficer(
            CreateClaimsOfficerRequest request);

    ClaimsOfficerResponse getOfficer(Long id);

    List<ClaimsOfficerResponse> getAllOfficers();

}