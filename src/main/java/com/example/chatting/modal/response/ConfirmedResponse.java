package com.example.chatting.modal.response;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@ToString
public class ConfirmedResponse {

    private ObjectId sender;
    private boolean seen;
    private LocalDateTime date = LocalDateTime.now();

}

