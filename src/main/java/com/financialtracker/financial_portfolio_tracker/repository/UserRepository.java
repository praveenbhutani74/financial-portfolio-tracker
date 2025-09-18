 package com.financialtracker.financial_portfolio_tracker.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financialtracker.financial_portfolio_tracker.entity.EUser;

public interface UserRepository extends JpaRepository<EUser, Integer> {
    boolean existsByEmail(String email);
    
    Optional<EUser> findByEmail(String email);

}
