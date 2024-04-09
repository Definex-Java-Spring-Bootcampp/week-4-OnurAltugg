package com.garantiservice.converter;

import org.springframework.stereotype.Component;

import com.garantiservice.dto.request.ApplicationRequest;
import com.garantiservice.dto.response.ApplicationResponse;
import com.garantiservice.entity.Application;
import com.garantiservice.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationConverter {

    public Application toApplication(ApplicationRequest request) {
        // @formatter:off
        return Application.builder()
                .appId(request.getAppId())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .applicationStatus(ApplicationStatus.INITIAL)
                .build();
        // @formatter:on
    }

    public ApplicationResponse toResponse(Application application) {
        return ApplicationResponse.builder()
                .appId(application.getAppId())
                .createDate(application.getCreateDate())
                .updateDate(application.getUpdateDate())
                .applicationStatus(application.getApplicationStatus())
                .build();
    }

    public List<ApplicationResponse> toResponseList(List<Application> applications) {
        List<ApplicationResponse> applicationResponses = new ArrayList<>();
        applications.forEach(application -> {
            applicationResponses.add(toResponse(application));
        });

        return applicationResponses;
    }
}
