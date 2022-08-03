package com.example.messaging.modal;

import com.example.messaging.modal.response.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Notification {
    @Id
    private String id;
    private ObjectId userId;
    private List<NotificationRequest> requestsComingFromOtherUsers;
    private List<Object> confirmedRequests;
}
