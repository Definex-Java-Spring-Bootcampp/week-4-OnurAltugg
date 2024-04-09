package com.kredinbizdeservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kredinbizdeservice.entity.Bank;
import com.kredinbizdeservice.service.BankService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banks")
@Tag(name = "Bank Controller")
public class BankController {
	
	private final BankService bankService;
	
	@GetMapping
	@Operation(summary = "Get Bank Details", description = "Retrieves details of all banks.")
	public List<Bank> getAll() {
		return bankService.getAll();
	}
	
	@GetMapping("/{bankId}")
	@Operation(summary = "Get Specified Bank Detail", description = "Retrieves details of the specified bank with bank id.")
	@Parameter(name = "Path variable bank id", description = "If the bank is not found, it throws 400 Not Found.", example = "4", required = true)
	public Bank getById(@PathVariable Long bankId) {
		return bankService.findById(bankId);
	}
	
	@PostMapping
	@Operation(summary = "Create Bank", description = "Creates a new bank.")
	@Parameter(name = "Request bank body",
			description = "Bank's name required. Id information is not required. If a request with the same bank name is made, 400 Bad Request is thrown.")
	public Bank save(@RequestBody Bank bank) {
		return bankService.save(bank);
	}
	
	@DeleteMapping("/{bankId}")
	@Operation(summary = "Delete Bank", description = "Deletes a bank.")
	@Parameter(name = "Path variable bank id",
			description = "Id information is required. If the bank is not found, it throws 400 Not Found.", example = "4", required = true)
	public void delete(@PathVariable Long bankId) {
		bankService.delete(bankId);
	}
}
