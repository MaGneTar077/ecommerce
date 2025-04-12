package com.ecommerce.usermicroservice.infraestructure.persistence.mapper;

import com.ecommerce.usermicroservice.domain.model.UserEvent;
import com.ecommerce.usermicroservice.infraestructure.persistence.entity.EventDocument;

public class EventMapper {

    public static EventDocument toDocument(UserEvent event) {

        EventDocument document = new EventDocument();

        document.setEventId(event.getEventId());
        document.setTimestamp(event.getTimestamp());
        document.setSource(event.getSource());
        document.setTopic(event.getTopic());
        document.setPayload(event.getPayload());
        document.setSnapshot(event.getSnapshot());

        return document;
    }

}
