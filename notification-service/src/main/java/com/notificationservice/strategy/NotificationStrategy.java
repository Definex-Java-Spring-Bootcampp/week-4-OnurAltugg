package com.notificationservice.strategy;

import com.notificationservice.dto.NotificationDTO;

public interface NotificationStrategy {
    void sendNotification(NotificationDTO notificationDTO);
}
