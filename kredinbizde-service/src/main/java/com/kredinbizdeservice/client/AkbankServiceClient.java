package com.kredinbizdeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.kredinbizdeservice.client.dto.response.ApplicationResponse;

@FeignClient(value = "akbank-service", url = "localhost:8081")
public interface AkbankServiceClient {

    @PostMapping("api/akbank/v1/applications")
    ApplicationResponse createApplication(@RequestBody ApplicationRequest request);
}
