package com.ecommerce.usermicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {

    private String eventId;
    private Instant timestamp;
    private String source;
    private String topic;
    private Map<String, Object> payload;
    private Map<String, Object> snapshot;

}
