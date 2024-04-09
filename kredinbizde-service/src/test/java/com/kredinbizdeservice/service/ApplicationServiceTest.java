package com.kredinbizdeservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kredinbizdeservice.entity.Application;
import com.kredinbizdeservice.enums.ApplicationStatus;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.ApplicationRepository;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
	
	@InjectMocks
	private ApplicationService applicationService;
	
	@Mock
	private ApplicationRepository applicationRepository;
	
	@Test
	@DisplayName("When valid application is given, create application")
	public void createApplicationTest() {
		Application application = createApplication();
		when(applicationRepository.findById(application.getId())).thenReturn(Optional.of(application));
		Application foundApplication = applicationService.findById(application.getId());
		assertEquals(application, foundApplication);
	}
	
	@Test
	@DisplayName("When id is given, find application by id or throw exception")
	public void throwKredinbizdeExceptionById() {
		Assertions.assertThrows(KredinbizdeException.class, () -> applicationService.findById(1L), "Application not found");
	}
	
	@Test
	@DisplayName("When id is given, delete application by id")
	public void deleteApplicationById() {
		Assertions.assertDoesNotThrow(() -> applicationService.deleteById(1L));
	}
	
	@Test
	@DisplayName("When id is given, update application by id")
	public void updateApplication() {
		Application application = createApplication();
		Application updatedApplication = new Application();
		updatedApplication.setId(1L);
		updatedApplication.setApplicationStatus(ApplicationStatus.IN_PROGRESS);
		when(applicationRepository.save(application)).thenReturn(updatedApplication);
		assertEquals(updatedApplication, applicationService.update(application));
	}
	
	private Application createApplication() {
		Application application = new Application();
		application.setId(1L);
		application.setApplicationStatus(ApplicationStatus.INITIAL);
		application.setCreateDate(LocalDateTime.now());
		return application;
	}
	
}
