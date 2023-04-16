package com.example.chatting.controller;

import com.example.chatting.modal.request.AuthenticationRequest;
import com.example.chatting.modal.request.RegisterRequest;
import com.example.chatting.modal.response.AuthenticationResponse;
import com.example.chatting.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        return authService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) throws Exception {
        authService.register(request);
    }

}
