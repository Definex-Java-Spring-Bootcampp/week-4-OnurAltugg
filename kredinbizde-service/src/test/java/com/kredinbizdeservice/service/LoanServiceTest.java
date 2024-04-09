package com.kredinbizdeservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kredinbizdeservice.entity.Loan;
import com.kredinbizdeservice.enums.LoanType;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.LoanRepository;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {
	
	@InjectMocks
	private LoanService loanService;
	
	@Mock
	private LoanRepository loanRepository;
	
	@Test
	@DisplayName("When valid loan is given, create loan")
	public void createLoanTest() {
		Loan loan = createLoan();
        when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));
        Loan foundLoan = loanService.findById(loan.getId());
        assertEquals(loan, foundLoan);
    }
	
	@Test
	@DisplayName("When id is given, find loan by id or throw exception")
    public void throwKredinbizdeExceptionById() {
		Assertions.assertThrows(KredinbizdeException.class, () -> loanService.findById(1L), "Loan not found");
	}
	
	@Test
	@DisplayName("When id is given, delete loan by id")
	public void deleteLoanById() {
		Assertions.assertDoesNotThrow(() -> loanService.deleteById(1L));
	}
	
	@Test
	@DisplayName("When loan is given, update loan")
	public void updateLoan() {
		Loan loan = createLoan();
		Loan updatedLoan = new Loan();
		updatedLoan.setId(1L);
		updatedLoan.setAmount(BigDecimal.valueOf(2000));
		when(loanRepository.save(loan)).thenReturn(updatedLoan);
		assertEquals(updatedLoan, loanService.update(loan));
	}
	
	@Test
	@DisplayName("When id is given, get bank by loan id")
	public void getBankByLoanId() {
		Loan loan = createLoan();
		when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));
		assertEquals(loan.getBank(), loanService.getBankByLoanId(loan.getId()));
	}
	
	private Loan createLoan() {
		Loan loan = new Loan();
		loan.setId(1L);
		loan.setAmount(BigDecimal.valueOf(1000));
		loan.setInstallment(12);
		loan.setInterestRate(1.2);
		loan.setLoanType(LoanType.ARAC_KREDISI);
		return loan;
	}
}
