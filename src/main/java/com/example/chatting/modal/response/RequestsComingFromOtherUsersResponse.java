package com.example.chatting.modal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestsComingFromOtherUsersResponse {
    private final ObjectId sender;
    private final boolean accepted;
    private final boolean ignored;
    private boolean seen;
    private final boolean active;
    private LocalDateTime date = LocalDateTime.now();
}
