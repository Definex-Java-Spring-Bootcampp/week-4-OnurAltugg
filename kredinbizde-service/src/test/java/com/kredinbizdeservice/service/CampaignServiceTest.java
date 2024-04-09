package com.kredinbizdeservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kredinbizdeservice.entity.Campaign;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.CampaignRepository;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {
	
	@InjectMocks
	private CampaignService campaignService;
	
	@Mock
	private CampaignRepository campaignRepository;
	
	@Test
	@DisplayName("When valid campaign is given, create campaign")
	public void createCampaignTest() {
		Campaign campaign = createCampaign();
		when(campaignRepository.findById(campaign.getId())).thenReturn(Optional.of(campaign));
		Campaign foundCampaign = campaignService.getById(campaign.getId());
		assertEquals(campaign, foundCampaign);
	}
	
	@Test
	@DisplayName("When id is given, find campaign by id or throw exception")
	public void throwKredinbizdeExceptionById() {
		Assertions.assertThrows(KredinbizdeException.class, () -> campaignService.getById(1L), "Campaign not found");
	}
	
	@Test
	@DisplayName("When campaign is given, update campaign")
	public void updateCampaign() {
		Campaign campaign = createCampaign();
		when(campaignRepository.save(campaign)).thenReturn(campaign);
		Campaign updatedCampaign = campaignService.update(campaign);
		assertEquals(campaign, updatedCampaign);
	}
	
	@Test
	@DisplayName("When id is given, delete campaign by id")
	public void deleteCampaignById() {
        Assertions.assertDoesNotThrow(() -> campaignService.delete(1L));
    }
	
    private Campaign createCampaign() {
		Campaign campaign = new Campaign();
		campaign.setId(1L);
		campaign.setContent("Campaign");
		campaign.setTitle("Campaign");
		campaign.setCreateDate(java.time.LocalDate.now());
		return campaign;
    }
}
