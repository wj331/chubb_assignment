package com.chubb.claims.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chubb.claims.dto.request.CreateClaimsOfficerRequest;
import com.chubb.claims.dto.response.ClaimsOfficerResponse;
import com.chubb.claims.entity.ClaimsOfficer;
import com.chubb.claims.exception.DuplicateResourceException;
import com.chubb.claims.exception.ResourceNotFoundException;
import com.chubb.claims.repository.ClaimsOfficerRepository;
import com.chubb.claims.service.ClaimsOfficerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClaimsOfficerServiceImpl
        implements ClaimsOfficerService {

    private final ClaimsOfficerRepository repository;

    @Override
    public ClaimsOfficerResponse createOfficer(
            CreateClaimsOfficerRequest request) {

        if (repository.existsByNameAndTeamId(
                request.getName(),
                request.getTeamId())) {

            throw new DuplicateResourceException(
                    "Officer already exists in this team.");
        }

        ClaimsOfficer officer = new ClaimsOfficer();

        officer.setName(request.getName());
        officer.setMarket(request.getMarket());
        officer.setTeamId(request.getTeamId());

        officer = repository.save(officer);

        log.info("Created officer {}", officer.getId());

        return mapToResponse(officer);
    }

    @Override
    public ClaimsOfficerResponse getOfficer(Long id) {

        ClaimsOfficer officer = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Officer not found with id " + id));

        return mapToResponse(officer);
    }

    @Override
    public List<ClaimsOfficerResponse> getAllOfficers() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ClaimsOfficerResponse mapToResponse(
            ClaimsOfficer officer) {

        return ClaimsOfficerResponse.builder()
                .id(officer.getId())
                .name(officer.getName())
                .market(officer.getMarket())
                .teamId(officer.getTeamId())
                .build();
    }

}