package com.ecommerce.NotificationMicroservice.adapter.in.kafka;

import com.ecommerce.NotificationMicroservice.application.processor.NotificationGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@RequiredArgsConstructor
public class ExternalEventConsumer {

   private final NotificationGenerator notificationGenerator;

    @KafkaListener(topics = { "welcome-flow", "cart-removals", "invoice-processing" }, groupId = "notification-service")
    public void consumeExternalEvent(ConsumerRecord<String, Map<String, Object>> record) {
        notificationGenerator.process(record.topic(), record.value());
    }
}
