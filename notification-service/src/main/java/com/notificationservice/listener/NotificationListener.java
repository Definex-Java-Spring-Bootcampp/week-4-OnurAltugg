package com.notificationservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.notificationservice.dto.NotificationDTO;
import com.notificationservice.strategy.NotificationStrategy;

@Component
@RequiredArgsConstructor
public class NotificationListener {
	
	private final NotificationStrategy notificationStrategy;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void sendNotification(@Qualifier("emailNotification")NotificationDTO notificationDTO) {
    	notificationStrategy.sendNotification(notificationDTO);
    }

}
