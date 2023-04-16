package com.example.chatting.entity;

import com.example.chatting.modal.response.ConfirmedResponse;
import com.example.chatting.modal.response.RequestsComingFromOtherUsersResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Notification {
    @Id
    private String id;
    private ObjectId userId;
    private List<RequestsComingFromOtherUsersResponse> requestsComingFromOtherUsers;
    private List<ConfirmedResponse> confirmedRequests;
}
