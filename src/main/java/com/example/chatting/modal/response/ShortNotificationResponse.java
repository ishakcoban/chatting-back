package com.example.chatting.modal.response;

import com.example.chatting.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShortNotificationResponse {
    private final User sender;
    private final boolean seen;
    private  boolean accepted;
    private  boolean ignored;
    private final LocalDateTime date;
}
