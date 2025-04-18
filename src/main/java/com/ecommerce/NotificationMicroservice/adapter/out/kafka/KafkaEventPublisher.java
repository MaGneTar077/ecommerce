package com.ecommerce.NotificationMicroservice.adapter.out.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {

    private final KafkaTemplate<String, Object>kafkaTemplate;

    public void publish(String topic, Object message){
        kafkaTemplate.send(topic, message)
                .whenComplete((res, ex) -> {
                    if(ex!=null){
                        System.err.println("Error al publicar a " + topic + ": "+ex.getMessage());
                    }else {
                        System.out.println("Evento publicado a "+ topic);
                    }

                });
    }
}
