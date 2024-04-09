package com.kredinbizdeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kredinbizdeservice.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
