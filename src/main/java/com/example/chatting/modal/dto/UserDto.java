package com.example.chatting.modal.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String gender;
    private String photoUrl;
    private String email;

}
