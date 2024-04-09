package com.kredinbizdeservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kredinbizdeservice.entity.Bank;
import com.kredinbizdeservice.entity.Loan;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.LoanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {
	
	private final LoanRepository loanRepository;
	
	public void save(Loan loan) {
		loanRepository.save(loan);
	}
	
	public Loan findById(Long id) {
		Optional<Loan> foundLoan = loanRepository.findById(id);
		Loan loan = foundLoan.orElseThrow(() -> new KredinbizdeException("Loan not found."));
		if (foundLoan.isPresent()) {
			loan = foundLoan.get();
		}
		return loan;
	}
	
	public void deleteById(Long id) {
		loanRepository.deleteById(id);
	}
	
	public Loan update(Loan loan) {
		return loanRepository.save(loan);
	}
	
	public Bank getBankByLoanId(Long id) {
		return findById(id).getBank();
	}
}
