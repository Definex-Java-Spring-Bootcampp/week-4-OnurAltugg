package com.kredinbizdeservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kredinbizdeservice.entity.Campaign;
import com.kredinbizdeservice.service.CampaignService;
import com.kredinbizdeservice.service.CreditCardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
@Tag(name = "Campaign Controller")
public class CampaignController {
	
	private final CampaignService campaignService;
	private final CreditCardService creditCardService;
	
	@GetMapping
	@Operation(summary = "Get Campaign Details", description = "Retrieves details of all campaigns.")
	public List<Campaign> getAll() {
		return campaignService.getAll();
	}
	
	@GetMapping("/{campaignId}")
	@Operation(summary = "Get Specified Campaign Detail", description = "Retrieves details of the specified campaign with campaign id.")
	@Parameter(name = "Path variable campaign id", description = "If the campaign is not found, it throws 400 Not Found.", example = "3", required = true)
	public Campaign getById(@PathVariable Long campaignId) {
		return campaignService.getById(campaignId);
	}
	
	@PostMapping("/{creditCardId}")
	@Operation(summary = "Create Campaign for Credit Card", description = "Create a campaign for a credit card with have a id.",
	parameters = {
			@Parameter(name = "Path variable credit card id", description = "If the credit card is not found, it throws 400 Not Found.",
					example = "2", required = true),
			@Parameter(name = "Request campaign body", description = "Title, content, due date and sector information of the campaign are given."
					+ "This campaign will be added to the credit card.",
			required = true)
	})
	public Campaign save(@PathVariable Long creditCardId, @RequestBody Campaign campaign) {
		campaign.setCreditCard(creditCardService.findById(creditCardId));
		return campaignService.save(campaign);
	}
	
	@DeleteMapping("/{campaignId}")
	@Operation(summary = "Delete Campaign", description = "Deletes a campaign.")
	@Parameter(name = "Path variable campaign id", description = "If the campiagn is not found, it throws 400 Not Found.", example = "3", required = true)
	public void delete(@PathVariable Long campaignId) {
		campaignService.delete(campaignId);
	}
	
}
