package com.kredinbizdeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kredinbizdeservice.entity.Campaign;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.CampaignRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignService {
	private final CampaignRepository campaignRepository;
	
	public Campaign save(Campaign campaign) {
		campaign.setCreateDate(java.time.LocalDate.now());
		campaign.setUpdateDate(java.time.LocalDate.now());
		return campaignRepository.save(campaign);
	}
	
	public Campaign getById(Long id) {
		Optional<Campaign> foundCampaign = campaignRepository.findById(id);
		Campaign campaign = foundCampaign.orElseThrow(() -> new KredinbizdeException("Campaign not found."));
		if (foundCampaign.isPresent()) {
			campaign = foundCampaign.get();
		}
		return campaign;
	}
	
	public List<Campaign> getAll() {
		return campaignRepository.findAll();
	}
	
	public void delete(Long id) {
		campaignRepository.deleteById(id);
	}
	
	public Campaign update(Campaign campaign) {
		campaign.setUpdateDate(java.time.LocalDate.now());
		return campaignRepository.save(campaign);
	}
}
