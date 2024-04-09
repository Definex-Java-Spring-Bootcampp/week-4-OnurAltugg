package com.kredinbizdeservice.converter;

import org.springframework.stereotype.Component;

import com.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.kredinbizdeservice.entity.Application;
import com.kredinbizdeservice.entity.User;
import com.kredinbizdeservice.enums.ApplicationStatus;

import java.time.LocalDateTime;

@Component
public class ApplicationConverter {

    public Application toApplication(ApplicationRequest request, User user) {
        Application application = new Application();
        application.setId(request.getAppId());
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setUpdateDate(LocalDateTime.now());
        application.setUser(user);
        application.setCreateDate(LocalDateTime.now());
        return application;
    }
}
