package com.example.chatting.modal.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String userName;
  private String firstName;
  private String lastName;
  private String gender;
  private String email;
  private String photoUrl;
  private String password;

}
