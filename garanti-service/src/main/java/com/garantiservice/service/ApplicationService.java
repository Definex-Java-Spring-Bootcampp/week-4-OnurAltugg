package com.garantiservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.garantiservice.converter.ApplicationConverter;
import com.garantiservice.dto.request.ApplicationRequest;
import com.garantiservice.dto.response.ApplicationResponse;
import com.garantiservice.entity.Application;
import com.garantiservice.repository.ApplicationRepository;

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
