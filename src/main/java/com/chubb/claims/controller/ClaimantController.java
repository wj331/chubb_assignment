package com.chubb.claims.controller;

import com.chubb.claims.dto.request.CreateClaimantRequest;
import com.chubb.claims.dto.response.ClaimantResponse;
import com.chubb.claims.service.ClaimantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claimants")
public class ClaimantController {

    private final ClaimantService claimantService;

    public ClaimantController(ClaimantService claimantService) {
        this.claimantService = claimantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClaimantResponse createClaimant(
            @Valid @RequestBody CreateClaimantRequest request) {

        return claimantService.createClaimant(request);
    }

    @GetMapping("/{id}")
    public ClaimantResponse getClaimant(
            @PathVariable Long id) {

        return claimantService.getClaimant(id);
    }

}