package com.example.messaging.modal.response;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WebSocketResponse {
    private String senderId;
    private String receiverId;
}
