package com.ecommerce.usermicroservice.infraestructure.producer;

import com.ecommerce.usermicroservice.domain.model.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void publish(UserEvent event) {
        kafkaTemplate.send(event.getTopic(), event);
    }

}
