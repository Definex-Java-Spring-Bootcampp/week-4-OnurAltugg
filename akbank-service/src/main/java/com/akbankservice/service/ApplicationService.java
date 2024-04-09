package com.akbankservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.akbankservice.converter.ApplicationConverter;
import com.akbankservice.dto.request.ApplicationRequest;
import com.akbankservice.dto.response.ApplicationResponse;
import com.akbankservice.entity.Application;
import com.akbankservice.repository.ApplicationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationConverter applicationConverter;


    public ApplicationResponse createApplication(ApplicationRequest request) {
        Application application = applicationConverter.toApplication(request);
        return applicationConverter.toResponse(applicationRepository.save(application));
    }


    public List<ApplicationResponse> getAll() {
        List<Application> applications = applicationRepository.findAll();
        return applicationConverter.toResponseList(applications);
    }
}
