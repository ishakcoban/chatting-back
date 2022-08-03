package com.example.messaging.modal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class NotificationRequest {
    private final String sender;
    private final boolean accepted;
    private final boolean ignored;
    private final boolean seen;
    private final boolean active;
    private LocalDateTime date = LocalDateTime.now();
}
