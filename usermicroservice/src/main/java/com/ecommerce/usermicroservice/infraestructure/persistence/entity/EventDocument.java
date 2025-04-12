package com.ecommerce.usermicroservice.infraestructure.persistence.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "user_events")
@Data
public class EventDocument {

    @Id
    private String eventId;
    private Instant timestamp;
    private String source;
    private String topic;
    private Map<String, Object> payload;
    private Map<String, Object> snapshot;

}
