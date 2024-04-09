package com.kredinbizdeservice.producer.dto;

import com.kredinbizdeservice.producer.enums.NotificationType;
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
