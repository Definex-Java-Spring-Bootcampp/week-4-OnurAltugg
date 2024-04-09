package com.kredinbizdeservice.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.kredinbizdeservice.configuration.RabbitMQConfiguration;
import com.kredinbizdeservice.producer.dto.NotificationDTO;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final RabbitMQConfiguration rabbitMQConfiguration;
    private final AmqpTemplate amqpTemplate;

    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Notification sent: {}", notificationDTO);
        amqpTemplate.convertSendAndReceive(rabbitMQConfiguration.getExchange(), rabbitMQConfiguration.getRoutingkey(), notificationDTO);
    }

}
