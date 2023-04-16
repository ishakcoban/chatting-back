package com.example.chatting.modal.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ConfirmFriendRequest {
    private final String sender;
    private final boolean seen;
    private LocalDateTime date = LocalDateTime.now();
}
