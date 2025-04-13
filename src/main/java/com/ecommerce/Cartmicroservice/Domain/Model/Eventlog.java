package com.ecommerce.Cartmicroservice.Domain.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Eventlog")
public class Eventlog {

    @Id
    private String eventId;
    private String timestamp;
    private String source;
    private String topic;
    private Object payload;
    private Object snapshot;

}
