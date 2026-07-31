package com.chubb.claims.service.impl;

import org.springframework.stereotype.Service;

import com.chubb.claims.dto.response.DashboardResponse;
import com.chubb.claims.enums.ClaimStatus;
import com.chubb.claims.enums.Market;
import com.chubb.claims.repository.ClaimRepository;
import com.chubb.claims.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final ClaimRepository claimRepository;

    @Override
    public DashboardResponse getDashboard(
            Market market) {

        if (market == null) {

            return DashboardResponse.builder()

                    .submitted(
                            claimRepository.countByStatus(
                                    ClaimStatus.SUBMITTED))

                    .underReview(
                            claimRepository.countByStatus(
                                    ClaimStatus.UNDER_REVIEW))

                    .waitingForInformation(
                            claimRepository.countByStatus(
                                    ClaimStatus.WAITING_FOR_INFORMATION))

                    .approved(
                            claimRepository.countByStatus(
                                    ClaimStatus.APPROVED))

                    .rejected(
                            claimRepository.countByStatus(
                                    ClaimStatus.REJECTED))

                    .settled(
                            claimRepository.countByStatus(
                                    ClaimStatus.SETTLED))

                    .closed(
                            claimRepository.countByStatus(
                                    ClaimStatus.CLOSED))

                    .outstandingLiability(
                            claimRepository.getOutstandingLiability())

                    .build();
        }

        return DashboardResponse.builder()

                .submitted(
                        claimRepository.countByStatusAndMarket(
                                ClaimStatus.SUBMITTED,
                                market))

                .underReview(
                        claimRepository.countByStatusAndMarket(
                                ClaimStatus.UNDER_REVIEW,
                                market))

                .waitingForInformation(
                        claimRepository.countByStatusAndMarket(
                                ClaimStatus.WAITING_FOR_INFORMATION,
                                market))

                .approved(
                        claimRepository.countByStatusAndMarket(
                                ClaimStatus.APPROVED,
                                market))

                .rejected(
                        claimRepository.countByStatusAndMarket(
                                ClaimStatus.REJECTED,
                                market))

                .settled(
                        claimRepository.countByStatusAndMarket(
                                ClaimStatus.SETTLED,
                                market))

                .closed(
                        claimRepository.countByStatusAndMarket(
                                ClaimStatus.CLOSED,
                                market))

                .outstandingLiability(
                        claimRepository.getOutstandingLiability(
                                market))

                .build();
    }

}