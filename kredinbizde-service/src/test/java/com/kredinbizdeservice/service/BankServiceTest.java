package com.kredinbizdeservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kredinbizdeservice.entity.Bank;
import com.kredinbizdeservice.entity.CreditCard;
import com.kredinbizdeservice.entity.Loan;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.BankRepository;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
	
	@InjectMocks
	private BankService bankService;
	
	@Mock
	private BankRepository bankRepository;
	
	@Test
	@DisplayName("When valid bank is given, create bank")
	public void createBankTest() {
		Bank bank = createBank();
		when(bankRepository.findById(bank.getId())).thenReturn(Optional.of(bank));
		Bank foundBank = bankService.findById(bank.getId());
		assertEquals(bank, foundBank);
	}
	
	@Test
	@DisplayName("When id is given, find bank by id or throw exception")
	public void throwKredinbizdeExceptionById() {
		Assertions.assertThrows(KredinbizdeException.class, () -> bankService.findById(1L), "Bank not found");
	}
	
	@Test
	@DisplayName("When id is given, delete bank by id")
	public void deleteBankById() {
		Assertions.assertDoesNotThrow(() -> bankService.delete(1L));
	}
	
	@Test
	@DisplayName("When bank is given, update bank")
	public void updateBank() {
		Bank bank = createBank();
		Bank updatedBank = createBank();
		updatedBank.setName("Garanti");
		when(bankRepository.save(bank)).thenReturn(updatedBank);
		Bank result = bankService.update(bank);
		assertEquals(result, updatedBank);
	}
	
	@Test
	@DisplayName("When id is given, get all credit cards of bank")
	public void getAllCreditCards() {
		Bank bank = createBank();
		List<CreditCard> creditCards = new ArrayList<>();
		creditCards.add(new CreditCard());
		bank.setCreditCards(creditCards);
		when(bankRepository.findById(bank.getId())).thenReturn(Optional.of(bank));
		List<CreditCard> bankCreditCards = bankService.getAllCreditCards(bank.getId());
		assertEquals(creditCards, bankCreditCards);
	}
	
	@Test
	@DisplayName("When id is given, get all loans of bank")
	public void getAllLoans() {
		Bank bank = createBank();
		List<Loan> loanList = new ArrayList<>();
		loanList.add(new Loan());
		bank.setLoanList(loanList);
		when(bankRepository.findById(bank.getId())).thenReturn(Optional.of(bank));
		List<Loan> bankLoans = bankService.getAllLoans(bank.getId());
		assertEquals(loanList, bankLoans);
	}
	
	private Bank createBank() {
		Bank bank = new Bank();
		bank.setId(1L);
		bank.setName("Akbank");
		return bank;
	}
}
