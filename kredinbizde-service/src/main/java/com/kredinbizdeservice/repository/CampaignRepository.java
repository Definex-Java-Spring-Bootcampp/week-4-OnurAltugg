package com.kredinbizdeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kredinbizdeservice.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long>{

}
