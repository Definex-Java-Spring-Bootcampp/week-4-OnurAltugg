package com.kredinbizdeservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kredinbizdeservice.entity.Application;
import com.kredinbizdeservice.entity.User;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	@DisplayName("When valid user is given, create user")
	public void createUserTest() {
		User user = createUser();
		when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));
        User foundUser = userService.findByEmail("test@gmail.com");
        assertEquals(user, foundUser);
	}
	
	@Test
	@DisplayName("When email is given, find user by email or throw exception")
    public void throwKredinbizdeException() {
        Assertions.assertThrows(KredinbizdeException.class, () -> userService.findByEmail("test@gmail.com"), "User not found");
    }
	
	@Test
	@DisplayName("When id is given, find user by id or throw exception")
    public void throwKredinbizdeExceptionById() {
		Assertions.assertThrows(KredinbizdeException.class, () -> userService.findById(1L), "User not found");
	}
	
	@Test
	@DisplayName("When id is given, delete user by id")
	public void deleteUserById() {
		Assertions.assertDoesNotThrow(() -> userService.deleteById(1L));
	}
	
	@Test
	@DisplayName("When valid user is given, update user")
	public void updateUser() {
	    User user = createUser();
	    
	    User updatedUser = new User();
	    updatedUser.setId(1L);
	    updatedUser.setName("Onur");
	    updatedUser.setSurname("Altug");
	    updatedUser.setEmail("onuraltug@.com");
	    updatedUser.setPassword("newpassword123");

	    when(userRepository.save(user)).thenReturn(updatedUser);

	    User result = userService.update(user);

	    assertEquals(updatedUser, result);
	    verify(userRepository, times(1)).save(user);
	}
	
	@Test
	@DisplayName("Find all users")
	public void findAllUsers() {
		assertThat(userService.findAll()).isNotNull();
	}
	
	@Test
	@DisplayName("When user id is given, find all applications by user id")
	public void findAllApplicationsByUserId() {
		User user = createUser();
		
		List<Application> applications = new ArrayList<>();
        applications.add(new Application());
        applications.add(new Application());
        user.setApplications(applications);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        List<Application> userApplications = userService.findUserApplications(user.getId());

        assertEquals(applications, userApplications);
        verify(userRepository, times(1)).findById(user.getId()); 
	}
	
	
	private User createUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setPassword("123456");
        user.setName("Test");
        user.setSurname("Test");
        return user;
	}

}
