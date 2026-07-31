package com.chubb.claims.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long submitted;

    private long underReview;

    private long waitingForInformation;

    private long approved;

    private long rejected;

    private long settled;

    private long closed;

    private BigDecimal outstandingLiability;

}