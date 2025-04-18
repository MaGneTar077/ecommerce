package com.ecommerce.NotificationMicroservice.adapter.in.kafka;

import com.ecommerce.NotificationMicroservice.application.processor.NotificationDispatcher;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

 private final NotificationDispatcher notificationDispatcher;

    @KafkaListener(topics = "notifications", groupId = "notification-service")
    public void handleNotification(ConsumerRecord<String, Map<String, Object>>record){
        notificationDispatcher.process(record.value());
    }
}
