package com.chubb.claims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chubb.claims.entity.ClaimsOfficer;

public interface ClaimsOfficerRepository
        extends JpaRepository<ClaimsOfficer, Long> {

    boolean existsByNameAndTeamId(String name, String teamId);

    Optional<ClaimsOfficer> findByNameAndTeamId(String name, String teamId);

}