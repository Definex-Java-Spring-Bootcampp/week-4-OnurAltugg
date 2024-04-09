package com.kredinbizdeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kredinbizdeservice.entity.Bank;
import com.kredinbizdeservice.entity.Campaign;
import com.kredinbizdeservice.entity.CreditCard;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.CreditCardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardService {
	
	private final CreditCardRepository creditCardRepository;
	
	public CreditCard save(CreditCard creditCard) {
		return creditCardRepository.save(creditCard);
	}
	
	public CreditCard findById(Long id) {
		Optional<CreditCard> foundCreditCard = creditCardRepository.findById(id);
		CreditCard creditCard = foundCreditCard.orElseThrow(() -> new KredinbizdeException("Credit card not found."));
		if (foundCreditCard.isPresent()) {
			creditCard = foundCreditCard.get();
		}
		return creditCard;
	}
	
	public void deleteById(Long id) {
		creditCardRepository.deleteById(id);
	}
	
	public CreditCard update(CreditCard creditCard) {
		return creditCardRepository.save(creditCard);
	}
	
	public List<Campaign> getCampaignsByCreditCardId(Long id) {
		return findById(id).getCampaignList();
	}
	
	public Bank getBankByCreditCardId(Long id) {
		return findById(id).getBank();
	}
}
