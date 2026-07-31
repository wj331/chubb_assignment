package com.chubb.claims.service.impl;

import com.chubb.claims.dto.request.AssignClaimRequest;
import com.chubb.claims.dto.request.CreateClaimRequest;
import com.chubb.claims.dto.response.ClaimResponse;
import com.chubb.claims.entity.Claim;
import com.chubb.claims.entity.Claimant;
import com.chubb.claims.entity.ClaimsOfficer;
import com.chubb.claims.exception.ResourceNotFoundException;
import com.chubb.claims.exception.InvalidClaimOperationException;
import com.chubb.claims.repository.ClaimRepository;
import com.chubb.claims.repository.ClaimantRepository;
import com.chubb.claims.repository.ClaimsOfficerRepository;
import com.chubb.claims.service.ClaimService;
import com.chubb.claims.enums.ClaimStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final ClaimantRepository claimantRepository;
    private final ClaimsOfficerRepository claimsOfficerRepository;

    @Override
    public ClaimResponse createClaim(CreateClaimRequest request) {

        Claimant claimant = claimantRepository.findById(request.getClaimantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Claimant not found with id " + request.getClaimantId()));

        Claim claim = new Claim();

        claim.setClaimant(claimant);
        claim.setClaimNumber(generateClaimNumber(request));
        claim.setStatus(ClaimStatus.SUBMITTED);

        claim.setClaimType(request.getClaimType());
        claim.setMarket(request.getMarket());
        claim.setDescription(request.getDescription());
        claim.setIncidentDate(request.getIncidentDate());

        claim = claimRepository.save(claim);

        log.info("Created claim {}", claim.getClaimNumber());

        return mapToResponse(claim);
    }

    @Override
    public ClaimResponse getClaim(Long id) {

        Claim claim = claimRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Claim not found with id " + id));

        return mapToResponse(claim);
    }

    private String generateClaimNumber(CreateClaimRequest request) {

        String market = request.getMarket().name();

        int year = Year.now().getValue();

        String unique =
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 8)
                        .toUpperCase();

        return String.format(
                "CLM-%s-%d-%s",
                market,
                year,
                unique
        );
    }

    @Override
    public ClaimResponse assignOfficer(Long claimId, AssignClaimRequest request) {
        Claim claim = claimRepository.findById(claimId)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Claim not found with id " + claimId));
        if (claim.getAssignedOfficer() != null) {
        throw new InvalidClaimOperationException(
                "Claim is already assigned to an officer.");
        }

        ClaimsOfficer officer = claimsOfficerRepository
        .findById(request.getOfficerId())
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Officer not found with id "
                                + request.getOfficerId()));

        // Optional business rule:
        // Only officers from the same market can handle the claim.
        if (officer.getMarket() != claim.getMarket()) {
        throw new IllegalArgumentException(
                "Officer and claim must belong to the same market.");
        }

        claim.setAssignedOfficer(officer);

        claim = claimRepository.save(claim);

        log.info("Assigned officer {} to claim {}",
        officer.getId(),
        claim.getClaimNumber());
        return mapToResponse(claim);
    }

    private ClaimResponse mapToResponse(Claim claim) {

        return ClaimResponse.builder()
                .id(claim.getId())
                .claimNumber(claim.getClaimNumber())
                .claimantName(claim.getClaimant().getName())
                .assignedOfficerName(
                        claim.getAssignedOfficer() == null ?
                                null :
                                claim.getAssignedOfficer().getName())
                .market(claim.getMarket())
                .status(claim.getStatus())
                .claimType(claim.getClaimType())
                .description(claim.getDescription())
                .incidentDate(claim.getIncidentDate())
                .estimatedLiability(claim.getEstimatedLiability())
                .decisionReason(claim.getDecisionReason())
                .createdAt(claim.getCreatedAt())
                .updatedAt(claim.getUpdatedAt())
                .build();
    }

}