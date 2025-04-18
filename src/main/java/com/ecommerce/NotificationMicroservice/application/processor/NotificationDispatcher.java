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
public class NotificationDispatcher {

    private final KafkaEventPublisher kafkaEventPublisher;
    private final EventMongoRepository eventMongoRepository;

    public void process(Map<String, Object> event) {
        try {

            String eventId = event.containsKey("eventId") ? event.get("eventId").toString() : UUID.randomUUID().toString();
            Instant timestamp = event.containsKey("timestamp") ? Instant.parse(event.get("timestamp").toString()) : Instant.now();
            String source = event.getOrDefault("source", "unknown-source").toString();
            String topic = event.getOrDefault("topic", "unknown-topic").toString();

            Map<String, Object> payload = (Map<String, Object>) event.getOrDefault("payload", Map.of());
            Map<String, Object> snapshot = (Map<String, Object>) event.getOrDefault("snapshot", Map.of());


            EventDocument eventDocument = EventDocument.builder()
                    .eventId(eventId)
                    .timestamp(timestamp)
                    .source(source)
                    .topic(topic)
                    .payload(payload)
                    .snapshot(snapshot.isEmpty() ? Map.of(
                            "userId", payload.getOrDefault("userId", "unknown"),
                            "message", payload.getOrDefault("message", "Sin mensaje")
                    ) : snapshot)
                    .build();


            eventMongoRepository.save(eventDocument);
            System.out.println("✅ Evento dinámico guardado en Mongo");


            kafkaEventPublisher.publish("email-service", eventDocument);
            System.out.println("📤 Enviado a topic 'email-service'");

        } catch (Exception e) {
            System.err.println("❌ Error al procesar evento flexible:");
            e.printStackTrace();
        }
    }
}
