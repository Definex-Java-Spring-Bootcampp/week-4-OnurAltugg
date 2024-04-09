package com.notificationservice.strategy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.notificationservice.dto.NotificationDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Primary
public class EmailNotification implements NotificationStrategy {

	@Override
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Email sent: {}", notificationDTO);
    }

}
