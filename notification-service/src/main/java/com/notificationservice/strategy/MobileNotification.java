package com.notificationservice.strategy;

import org.springframework.stereotype.Component;

import com.notificationservice.dto.NotificationDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MobileNotification implements NotificationStrategy {

	@Override
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Mobil notification sent: {}", notificationDTO);
    }

}
