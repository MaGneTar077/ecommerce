package com.api.product.Domain.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventProduct {
    private String eventId;
    private LocalDateTime timestamp;
    private String source;
    private Map<String, Object> payload;
    private Map<String, Object> snapshot;

}
