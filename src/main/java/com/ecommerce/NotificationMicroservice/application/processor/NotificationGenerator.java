package com.ecommerce.NotificationMicroservice.application.processor;

import com.ecommerce.NotificationMicroservice.adapter.out.kafka.KafkaEventPublisher;
import com.ecommerce.NotificationMicroservice.adapter.out.mongo.EventMongoRepository;
import com.ecommerce.NotificationMicroservice.model.EventDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationGenerator {

    private final KafkaEventPublisher kafkaEventPublisher;
    private final EventMongoRepository eventMongoRepository;

    public void process(String topic, Map<String, Object>message){
        String userId = ((Map<String, Object>)message.get("payload")).getOrDefault("userId", "N/A").toString();

        EventDocument event = EventDocument.builder()
                .eventId(UUID.randomUUID().toString())
                .timestamp(Instant.now())
                .source("external-event")
                .topic(topic)
                .payload(message)
                .snapshot(Map.of(
                        "message", generateMessage(topic, message),
                        "userId", userId
                ))
                .build();

        eventMongoRepository.save(event);
        kafkaEventPublisher.publish("notifications",event);
    }

    private String generateMessage(String topic, Map<String, Object>message){
        String name = ((Map<String, Object>)message.get("payload")).getOrDefault("name","Usuario").toString();
        return switch (topic){
            case "welcome-flow" -> "¡Bienvenido, " + name + "!";
            case "cart-removals" -> "Recordatorio: tienes productos olvidados en el carrito.";
            case "invoice-processing" -> "Tu compra ha sido facturada exitosamente.";
            default -> "Nueva notificación disponible.";
        };
    }

}
