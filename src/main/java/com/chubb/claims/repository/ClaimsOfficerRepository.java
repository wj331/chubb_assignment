package com.chubb.claims.repository;

import com.chubb.claims.entity.ClaimsOfficer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimsOfficerRepository extends JpaRepository<ClaimsOfficer, Long> {
}