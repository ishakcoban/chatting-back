package com.example.messaging.modal.response;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MessageResponse {

    @Id
    private String id;
    private String sender;
    private String content;
    private LocalDateTime dateTime;

}
