package com.example.chatting.service;

import com.example.chatting.entity.User;
import com.example.chatting.mapper.UserMapper;
import com.example.chatting.modal.request.AuthenticationRequest;
import com.example.chatting.modal.request.RegisterRequest;
import com.example.chatting.modal.response.AuthenticationResponse;
import com.example.chatting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;

    public void register(RegisterRequest request) throws Exception {

        userService.checkEmailExist(request.getEmail());
        User user = userMapper.register(request);
        userRepository.save(user);

    }

    public AuthenticationResponse login(AuthenticationRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid login or password", e);
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = tokenService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
