package com.chubb.claims.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chubb.claims.dto.response.DashboardResponse;
import com.chubb.claims.enums.Market;
import com.chubb.claims.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(

            @RequestParam(required = false)
            Market market) {

        return ResponseEntity.ok(
                dashboardService.getDashboard(
                        market));

    }

}