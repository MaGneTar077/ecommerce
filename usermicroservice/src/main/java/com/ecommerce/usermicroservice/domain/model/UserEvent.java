package com.ecommerce.usermicroservice.domain.model;

import java.time.Instant;
import java.util.Map;

public class UserEvent {

    private String eventId;
    private Instant timestamp;
    private String source;
    private String topic;
    private Map<String, Object> payload;
    private Map<String, Object> snapshot;

}
