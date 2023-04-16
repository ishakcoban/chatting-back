package com.example.chatting.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
public class SuccessMessage {

    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final String message;
    private final String path;
    private final String account;
    private final String status = HttpStatus.OK.toString();
}
