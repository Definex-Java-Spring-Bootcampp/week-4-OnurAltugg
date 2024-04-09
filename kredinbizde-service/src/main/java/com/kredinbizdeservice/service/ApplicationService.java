package com.kredinbizdeservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kredinbizdeservice.client.AkbankServiceClient;
import com.kredinbizdeservice.client.GarantiServiceClient;
import com.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.kredinbizdeservice.converter.ApplicationConverter;
import com.kredinbizdeservice.entity.Application;
import com.kredinbizdeservice.entity.User;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {
	
	private final ApplicationRepository applicationRepository;
	private final AkbankServiceClient akbankServiceClient;
    private final GarantiServiceClient garantiServiceClient;
    private final UserService userService;
    private final ApplicationConverter applicationConverter;
	
	public Application save(Long id, ApplicationRequest request) {
		User user = userService.findById(id);
		Application application = applicationConverter.toApplication(request, user);
		return applicationRepository.save(application);
	}
	
	public Application findById(Long id) {
		Optional<Application> foundApplication = applicationRepository.findById(id);
		Application application = foundApplication.orElseThrow(() -> new KredinbizdeException("Application not found."));
		if (foundApplication.isPresent()) {
			application = foundApplication.get();
		}
		return application;
	}
	
	public void deleteById(Long id) {
		applicationRepository.deleteById(id);
	}
	
	public Application update(Application application) {
		application.setUpdateDate(java.time.LocalDateTime.now());
		return applicationRepository.save(application);
	}
	
	public Application createAkbankApplication(Long userId, ApplicationRequest request) {
        User user = userService.findById(userId);
        Application application = applicationConverter.toApplication(request, user);
        akbankServiceClient.createApplication(request);
        return applicationRepository.save(application);
    }
    
    public Application createGarantiApplication(Long userId, ApplicationRequest request) {
    	User user = userService.findById(userId);
        Application application = applicationConverter.toApplication(request, user);
        garantiServiceClient.createApplication(request);
        return applicationRepository.save(application);
    }
    
}
