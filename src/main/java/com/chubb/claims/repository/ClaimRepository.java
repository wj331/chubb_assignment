package com.chubb.claims.repository;

import com.chubb.claims.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository
        extends JpaRepository<Claim, Long> {
}