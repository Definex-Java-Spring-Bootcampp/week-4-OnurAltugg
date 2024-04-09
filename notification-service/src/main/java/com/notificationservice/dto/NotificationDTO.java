package com.notificationservice.dto;

import com.notificationservice.dto.enums.NotificationType;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationDTO {

    private NotificationType notificationType;
    private String message;
    private String email;

}