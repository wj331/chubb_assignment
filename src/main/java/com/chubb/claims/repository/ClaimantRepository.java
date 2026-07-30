package com.chubb.claims.repository;

import com.chubb.claims.entity.Claimant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimantRepository extends JpaRepository<Claimant, Long> {
    boolean existsByEmail(String email);
}