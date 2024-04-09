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

import com.kredinbizdeservice.entity.Bank;
import com.kredinbizdeservice.entity.CreditCard;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.CreditCardRepository;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {
	
	@InjectMocks
	private CreditCardService creditCardService;
	
	@Mock
	private CreditCardRepository creditCardRepository;
	
	@Test
	@DisplayName("When valid credit card is given, create credit card")
	public void createCreditCardTest() {
		CreditCard creditCard = createCreditCard();
		when(creditCardRepository.findById(creditCard.getId())).thenReturn(Optional.of(creditCard));
		CreditCard foundCreditCard = creditCardService.findById(creditCard.getId());
		assertEquals(creditCard, foundCreditCard);
	}
	
	@Test
	@DisplayName("When id is given, find credit card by id or throw exception")
	public void throwKredinbizdeExceptionById() {
		Assertions.assertThrows(KredinbizdeException.class, () -> creditCardService.findById(1L),
				"Credit card not found");
	}
	
	@Test
	@DisplayName("When id is given, delete credit card by id")
	public void deleteCreditCardById() {
		Assertions.assertDoesNotThrow(() -> creditCardService.deleteById(1L));
	}
	
	@Test
	@DisplayName("When credit card is given, update credit card")
	public void updateCreditCard() {
		CreditCard creditCard = createCreditCard();
		CreditCard updatedCreditCard = new CreditCard();
		updatedCreditCard.setId(1L);
		updatedCreditCard.setFee(BigDecimal.valueOf(200));
		when(creditCardRepository.save(creditCard)).thenReturn(updatedCreditCard);
		CreditCard result = creditCardService.update(creditCard);
		assertEquals(result, updatedCreditCard);
	}
	
	@Test
	@DisplayName("When id is given, find credit card bank")
	public void findBankByCreditCardId() {
		CreditCard creditCard = createCreditCard();
		Bank bank = new Bank();
		creditCard.setBank(bank);
		when(creditCardRepository.findById(creditCard.getId())).thenReturn(Optional.of(creditCard));
		Bank foundBank = creditCardService.getBankByCreditCardId(creditCard.getId());
		assertEquals(foundBank, bank);
	}
	
	private CreditCard createCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setId(1L);
		creditCard.setFee(BigDecimal.valueOf(100));
		return creditCard;
	}
	
}
