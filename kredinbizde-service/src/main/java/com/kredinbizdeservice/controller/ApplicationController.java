package com.kredinbizdeservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kredinbizdeservice.entity.Application;
import com.kredinbizdeservice.entity.CreditCard;
import com.kredinbizdeservice.entity.Loan;
import com.kredinbizdeservice.service.ApplicationService;
import com.kredinbizdeservice.service.BankService;
import com.kredinbizdeservice.service.CreditCardService;
import com.kredinbizdeservice.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
@Tag(name = "Application Controller")
public class ApplicationController {
	
	private final ApplicationService applicationService;
	private final LoanService loanService;
	private final CreditCardService creditCardService;
	private final BankService bankService;
	
    @PostMapping("/{appId}/banks/{bankId}/loans")
    @Operation(summary = "Update Application", description = "Updates a application.",
    parameters = {
    		@Parameter(name = "Path variable app id", description = "If the app is not found, it throws 400 Not Found.", example = "10", required = true),
    		@Parameter(name = "Path variable bank id", description = "If the bank is not found, it throws 400 Not Found.", required = true),
    		@Parameter(name = "Request loan body", description = "It registers the loan in the bank where it is located and then in its application.", 
    		required = true)
    })
	public Loan createLoan(@PathVariable Long appId, @PathVariable Long bankId, @RequestBody Loan loan) {
		Application application = applicationService.findById(appId);
		loan.setBank(bankService.findById(bankId));
		application.setLoan(loan);
		loanService.save(loan);
		return loan;
	}
    
    @PostMapping("/{appId}/banks/{bankId}/credit-cards")
    @Operation(summary = "Update Application", description = "Updates a application.",
    parameters = {
    		@Parameter(name = "Path variable app id", description = "If the app is not found, it throws 400 Not Found.", example = "10", required = true),
    		@Parameter(name = "Path variable bank id", description = "If the bank is not found, it throws 400 Not Found.", required = true),
    		@Parameter(name = "Request credit-card body", description = "It registers the credit-card in the bank "
    				+ "where it is located and then in its application.", required = true)
    })
	public CreditCard createCreditCard(@PathVariable Long appId, @PathVariable Long bankId, @RequestBody CreditCard creditCard) {
		Application application = applicationService.findById(appId);
		application.setCreditCard(creditCard);
		creditCard.setBank(bankService.findById(bankId));
		creditCardService.save(creditCard);
		return creditCard;
	}
    
    @GetMapping("/{appId}")
    @Operation(summary = "Get Specified App Detail", description = "Retrieves details of the specified app with app id.")
	@Parameter(name = "Path variable app id", description = "If the app is not found, it throws 400 Not Found.", example = "10", required = true)
    public Application getById(@PathVariable Long appId) {	
    	return applicationService.findById(appId);
    }
    
    @GetMapping("/{appId}/loans")
    @Operation(summary = "Get Specified App's Loan Detail", description = "Retrieves details of the specified app's loan with app id.")
	@Parameter(name = "Path variable app id", description = "If the app is not found, it throws 400 Not Found.", example = "10", required = true)
	public Loan getLoan(@PathVariable Long appId) {
		Application application = applicationService.findById(appId);
		return application.getLoan();
	}
    
    @GetMapping("/{appId}/credit-cards")
    @Operation(summary = "Get Specified App's Credit-Card Detail", description = "Retrieves details of the specified app's credit-card with app id.")
	@Parameter(name = "Path variable app id", description = "If the app is not found, it throws 400 Not Found.", example = "10", required = true)
    public CreditCard getCreditCard(@PathVariable Long appId) {
    	Application application = applicationService.findById(appId);
    	return application.getCreditCard();
    }
    
    @PutMapping("/{appId}")
    @Operation(summary = "Update Application", description = "Updates a application.",
    parameters = {
    		@Parameter(name = "Path variable app id", description = "If the app is not found, it throws 400 Not Found.", example = "10", required = true),
    		@Parameter(name = "Request app body", description = "Application status can change. Update date is updated automatically.", required = true)
    })
	public Application update(@PathVariable Long appId, @RequestBody Application application) {
		Application foundApplication = applicationService.findById(appId);
		foundApplication.setApplicationStatus(application.getApplicationStatus());
		return applicationService.update(foundApplication);
	}
    
    @DeleteMapping("/{appId}")
    @Operation(summary = "Delete Application", description = "Deletes a application.")
	@Parameter(name = "Path variable app id", description = "If the app is not found, it throws 400 Not Found.", example = "3", required = true)
	public void delete(@PathVariable Long appId) {
		applicationService.deleteById(appId);
	}
}
