package com.chubb.claims.service.impl;

import com.chubb.claims.dto.request.CreateClaimantRequest;
import com.chubb.claims.dto.response.ClaimantResponse;
import com.chubb.claims.entity.Claimant;
import com.chubb.claims.exception.DuplicateResourceException;
import com.chubb.claims.exception.ResourceNotFoundException;
import com.chubb.claims.repository.ClaimantRepository;
import com.chubb.claims.service.ClaimantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimantServiceImpl implements ClaimantService {

    private final ClaimantRepository claimantRepository;

    @Override
    public ClaimantResponse createClaimant(CreateClaimantRequest request) {

        if (claimantRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        Claimant claimant = new Claimant();
        claimant.setName(request.getName());
        claimant.setEmail(request.getEmail());
        claimant.setPhone(request.getPhone());

        claimant = claimantRepository.save(claimant);

        log.info("Created claimant {}", claimant.getId());

        return toResponse(claimant);
    }

    @Override
    public ClaimantResponse getClaimant(Long id) {

        Claimant claimant = claimantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Claimant not found with id " + id));

        return toResponse(claimant);
    }

    private ClaimantResponse toResponse(Claimant claimant) {
        return ClaimantResponse.builder()
                .id(claimant.getId())
                .name(claimant.getName())
                .email(claimant.getEmail())
                .phone(claimant.getPhone())
                .build();
    }
}