package com.kredinbizdeservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kredinbizdeservice.client.dto.request.ApplicationRequest;
import com.kredinbizdeservice.entity.Application;
import com.kredinbizdeservice.entity.User;
import com.kredinbizdeservice.service.ApplicationService;
import com.kredinbizdeservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {
	
	private final UserService userService;
	private final ApplicationService applicationService;
	
	@PostMapping
	@Operation(summary = "Create User", description = "Creates a new user.")
	@Parameter(name = "Request user body",
			description = "User's name, surname, date of birth, email, password and phone number. Id information is not required.")
	public User create(@RequestBody User user ){
		return userService.save(user);
	}
	
	@GetMapping
	@Operation(summary = "Get User Details", description = "Retrieves details of all users.")
    public List<User> getAll() {
        return userService.findAll();
    }
    
    @GetMapping("/{userId}")
    @Operation(summary = "Get Specified User Detail", description = "Retrieves details of the specified user with user id.")
	@Parameter(name = "Path variable user id", description = "If the user is not found, it throws 400 Not Found.", example = "2", required = true)
	public User getById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
    
	@PutMapping
	@Operation(summary = "Update User", description = "Updates a user.")
	@Parameter(name = "Request user body",
			description = "Id information is required. If the user is not found, it throws 400 Not Found.")
	public User update(@RequestBody User user) {
		return userService.update(user);
	}

	@DeleteMapping("/{userId}")
	@Operation(summary = "Delete User", description = "Deletes a user.")
	@Parameter(name = "Path variable user id",
			description = "Id information is required. If the user is not found, it throws 400 Not Found.", example = "2", required = true)
	public void delete(@PathVariable Long userId) {
		userService.deleteById(userId);
	}
	
	@PostMapping("/{userId}/applications")
	@Operation(summary = "Create User Application", description = "Creates a new application for the specified user.", 
		parameters = {
				@Parameter(name = "Path variable user id", description = "ID of the user to create application for. "
						+ "If the user is not found, it throws 400 Not Found.", required = true, example = "2"),
				@Parameter(name = "Request app id", description = "Only the id of the application should be specified. "
						+ "Other values are created automatically.", required = true)})
	public Application createApplication(@PathVariable Long userId, @RequestBody ApplicationRequest request) {
        return applicationService.save(userId, request);
    }
	
	@GetMapping("/{userId}/applications")
	@Operation(summary = "Get User Applications Details", description = "Retrieves all applications of the specified user.")
	@Parameter(name = "Path variable user id",
		description = "Id information is required. If the user is not found, it throws 400 Not Found.", example = "2", required = true)
	public List<Application> getUserApplications(@PathVariable Long userId) {
		return userService.findUserApplications(userId);
	}
	
	@PostMapping("/{userId}/applications/akbank")
	@Operation(summary = "Create Akbank Application", description = "Creates a new application for Akbank for the specified user. "
			+ "The application registers to user applications and is sent to akbank-service and registers to the applications there.",
		parameters = {
		        @Parameter(name = "Path variable user id", description = "ID of the user to create Akbank application for. "
		        		+ "If the user is not found, it throws 400 Not Found.", required = true, example = "2"),
		        @Parameter(name = "Request body app id", description = "The id of the application to be created.", required = true, example = "13")})
	public Application createAkbankApplication(@PathVariable Long userId, @RequestBody ApplicationRequest request) {
		return applicationService.createAkbankApplication(userId, request);
	}
	
	@PostMapping("/{userId}/applications/garanti")
	@Operation(summary = "Create Garanti Application", description = "Creates a new application for Garanti for the specified user. "
			+ "The application registers to user applications and is sent to garanti-service and registers to the applications there.",
		parameters = {
		        @Parameter(name = "Path variable user id", description = "ID of the user to create Garanti application for. "
		        		+ "If the user is not found, it throws 400 Not Found.", required = true, example = "2"),
		        @Parameter(name = "Request body app id", description = "The id of the application to be created.", required = true, example = "13")})
	public Application createGarantiApplication(@PathVariable Long userId, @RequestBody ApplicationRequest request) {
		return applicationService.createGarantiApplication(userId, request);
	}
}
