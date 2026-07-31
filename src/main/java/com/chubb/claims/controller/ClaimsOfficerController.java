package com.chubb.claims.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chubb.claims.dto.request.CreateClaimsOfficerRequest;
import com.chubb.claims.dto.response.ClaimsOfficerResponse;
import com.chubb.claims.service.ClaimsOfficerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/officers")
@RequiredArgsConstructor
public class ClaimsOfficerController {

    private final ClaimsOfficerService claimsOfficerService;

    @PostMapping
    public ResponseEntity<ClaimsOfficerResponse> createOfficer(
            @Valid @RequestBody CreateClaimsOfficerRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(claimsOfficerService.createOfficer(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimsOfficerResponse> getOfficer(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                claimsOfficerService.getOfficer(id));
    }

    @GetMapping
    public ResponseEntity<List<ClaimsOfficerResponse>> getAllOfficers() {

        return ResponseEntity.ok(
                claimsOfficerService.getAllOfficers());
    }

}