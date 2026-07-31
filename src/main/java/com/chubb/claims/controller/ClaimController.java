package com.chubb.claims.controller;

import com.chubb.claims.dto.request.CreateClaimRequest;
import com.chubb.claims.dto.response.ClaimResponse;
import com.chubb.claims.service.ClaimService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<ClaimResponse> createClaim(
            @Valid @RequestBody CreateClaimRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(claimService.createClaim(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimResponse> getClaim(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                claimService.getClaim(id));
    }

}