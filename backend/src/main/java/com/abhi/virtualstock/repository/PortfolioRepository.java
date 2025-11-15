package com.abhi.virtualstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.virtualstock.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
    Portfolio getByUser_Email(String email);
    Portfolio getByUser_EmailAndLocked(String user_email, boolean locked);
}
