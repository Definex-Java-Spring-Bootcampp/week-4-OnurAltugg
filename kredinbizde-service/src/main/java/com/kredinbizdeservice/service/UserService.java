package com.kredinbizdeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kredinbizdeservice.entity.Application;
import com.kredinbizdeservice.entity.User;
import com.kredinbizdeservice.exceptions.KredinbizdeException;
import com.kredinbizdeservice.producer.NotificationProducer;
import com.kredinbizdeservice.producer.dto.NotificationDTO;
import com.kredinbizdeservice.producer.enums.NotificationType;
import com.kredinbizdeservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final NotificationProducer notificationProducer;
	
	public User save(User user){
		try {
	        user.setPassword(user.shaHashCode(user.getPassword()));
	        User savedUser = userRepository.save(user);
	        notificationProducer.sendNotification(
	            NotificationDTO.builder()
	                .message("User saved")
	                .notificationType(NotificationType.EMAIL)
	                .email(user.getEmail())
	                .build()
	        );
	        return savedUser;
	    } catch (DataIntegrityViolationException e) {
	        String errorMessage = "Duplicate entry error occurred. Email address is already in use.";
	        throw new DataIntegrityViolationException(errorMessage); 
	    }
	}
	
	public User findByEmail(String email) {
		Optional<User> foundUser = userRepository.findByEmail(email);
        User user = foundUser.orElseThrow(() -> new KredinbizdeException("User not found."));
        if (foundUser.isPresent()) {
            user = foundUser.get();
        }
        return user;
	}
	
	public User findById(Long id) {
		Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.orElseThrow(() -> new KredinbizdeException("User not found."));
        if (foundUser.isPresent()) {
            user = foundUser.get();
        }
        return user;
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<Application> findUserApplications(Long id) {
		User user = findById(id);
		return user.getApplications();
	}
	
}
