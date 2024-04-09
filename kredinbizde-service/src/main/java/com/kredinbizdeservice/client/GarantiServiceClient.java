package com.kredinbizdeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.kredinbizdeservice.client.dto.response.ApplicationResponse;


@FeignClient(value = "garanti-service", url = "localhost:8082")
public interface GarantiServiceClient {
	
	@PostMapping("/api/garanti/v1/applications")
	ApplicationResponse createApplication(@RequestBody ApplicationRequest request);
	
}
