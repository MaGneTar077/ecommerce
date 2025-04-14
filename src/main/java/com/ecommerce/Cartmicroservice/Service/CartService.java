package com.ecommerce.Cartmicroservice.Service;

import com.ecommerce.Cartmicroservice.Domain.Model.CartItem;
import com.ecommerce.Cartmicroservice.Domain.Model.Eventlog;
import com.ecommerce.Cartmicroservice.Domain.Repository.CartRepository;
import com.ecommerce.Cartmicroservice.Domain.Repository.EventlogRepository;
import com.ecommerce.Cartmicroservice.Dto.AddItemRequest;
import com.ecommerce.Cartmicroservice.Dto.RemoveItemRequest;
import com.ecommerce.Cartmicroservice.Kafka.KafkaProducer;
import com.ecommerce.Cartmicroservice.Util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

private final CartRepository cartRepository;
private final EventlogRepository eventlogRepository;
private final KafkaProducer kafkaProducer;

    public void addItem(AddItemRequest request){
       var item = cartRepository.findByUserIdAndProductId(request.getUserId(), request.getProductId()).orElse(new CartItem());
        item.setUserId(request.getUserId());
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());
        item.setUpdateAt(LocalDateTime.now());

        cartRepository.save(item);


        var snapshot = Map.of(
             "cartId","cart_"+request.getUserId(),
             "totalItems",
             cartRepository.findAllByUserId(request.getUserId()).size(),
             "updateAt", TimeUtils.nowUTC()

        );


        var event = new Eventlog();
        event.setEventId(UUID.randomUUID().toString());
        event.setTimestamp(TimeUtils.nowUTC());
        event.setSource("CartUpdated");
        event.setTopic("cart-updates");
        event.setPayload(request);
        event.setSnapshot(snapshot);

        kafkaProducer.sendMessage("cart-updates", event);
        eventlogRepository.save(event);
    }

    public void removeItem(String productId, RemoveItemRequest request){
        cartRepository.deleteByUserIdAndProductId(request.getUserId(), productId);


            var snaphot = Map.of(
                "userId",request.getUserId(),
                "productId",productId,
                "quantity",2

            );

                var event = new Eventlog();
                event.setEventId(UUID.randomUUID().toString());
                event.setTimestamp(TimeUtils.nowUTC());
                event.setSource("CartRemoval");
                event.setTopic("cart-removals");
                event.setPayload(request);
                event.setSnapshot(snaphot);

        kafkaProducer.sendMessage("cart-removals", event);
        eventlogRepository.save(event);
    }

}
