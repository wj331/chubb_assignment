package com.chubb.claims.repository;

import com.chubb.claims.entity.Claim;
import com.chubb.claims.enums.ClaimStatus;
import com.chubb.claims.enums.Market;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Optional<Claim> findByClaimNumber(String claimNumber);

    long countByStatus(ClaimStatus status);

    long countByStatusAndMarket(
            ClaimStatus status,
            Market market);

    @Query("""
            SELECT COALESCE(SUM(c.estimatedLiability),0)
            FROM Claim c
            WHERE c.status <> 'CLOSED'
            """)
    BigDecimal getOutstandingLiability();

    @Query("""
            SELECT COALESCE(SUM(c.estimatedLiability),0)
            FROM Claim c
            WHERE c.status <> 'CLOSED'
            AND c.market = :market
            """)
    BigDecimal getOutstandingLiability(Market market);
}