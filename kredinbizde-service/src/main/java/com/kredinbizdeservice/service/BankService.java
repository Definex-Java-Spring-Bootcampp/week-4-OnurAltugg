package com.kredinbizdeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kredinbizdeservice.entity.Bank;
import com.kredinbizdeservice.entity.CreditCard;
import com.kredinbizdeservice.entity.Loan;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.BankRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankService {

	private final BankRepository bankRepository;
	
	public Bank save(Bank bank) {
		try {
			Bank savedBank = bankRepository.save(bank);
			return savedBank;
		} catch (DataIntegrityViolationException e) {
			String errorMessage = "Duplicate entry error occurred. Bank name is already in use.";
			throw new DataIntegrityViolationException(errorMessage);
		}
	}
	
	public Bank findById(Long id) {
		Optional<Bank> foundBank = bankRepository.findById(id);
		Bank bank = foundBank.orElseThrow(() -> new KredinbizdeException("Bank not found."));
		if (foundBank.isPresent()) {
			bank = foundBank.get();
		}
		return bank;
	}
	
	public List<Bank> getAll() {
		return bankRepository.findAll();
	}
	
	public void delete(Long id) {
		bankRepository.deleteById(id);
	}
	
	public Bank update(Bank bank) {
		return bankRepository.save(bank);
	}
	
	public List<CreditCard> getAllCreditCards(Long id) {
		return findById(id).getCreditCards();
	}
	
	public List<Loan> getAllLoans(Long id) {
        return findById(id).getLoanList();
    }

}
