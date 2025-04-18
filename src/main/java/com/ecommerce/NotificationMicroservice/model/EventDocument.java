package com.ecommerce.NotificationMicroservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;


@Document(collection = "event_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDocument {

    @Id
    private String eventId;
    private Instant timestamp;
    private String source;
    private String topic;
    private Map<String, Object>payload;
    private Map<String, Object>snapshot;

}
