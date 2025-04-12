package com.ecommerce.usermicroservice.application.service;

import com.ecommerce.usermicroservice.application.dto.RegisterUserRequest;
import com.ecommerce.usermicroservice.application.usecase.RegisterUserUseCase;
import com.ecommerce.usermicroservice.domain.model.User;
import com.ecommerce.usermicroservice.domain.model.UserEvent;
import com.ecommerce.usermicroservice.domain.repository.EventStoreRepository;
import com.ecommerce.usermicroservice.domain.repository.UserRepository;
import com.ecommerce.usermicroservice.infraestructure.producer.UserEventProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final EventStoreRepository eventStoreRepository;
    private final ObjectMapper objectMapper;
    private final UserEventProducer userEventProducer;

    @Override
    public void register(RegisterUserRequest request) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        userRepository.save(user);

        Map<String, Object> userMap = objectMapper.convertValue(user, Map.class);

        UserEvent event = new UserEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setTimestamp(Instant.now());
        event.setSource("UserService");
        event.setTopic("user-registration");
        event.setPayload(userMap);
        event.setSnapshot(userMap);

        eventStoreRepository.save(event);

        userEventProducer.publish(event);
    }
}
