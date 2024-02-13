package com.example.testniveaujava.services;


import com.example.testniveaujava.auth.AuthenticationRequest;
import com.example.testniveaujava.auth.RegisterRequest;
import com.example.testniveaujava.entites.Client;
import com.example.testniveaujava.entites.Role;
import com.example.testniveaujava.exceptions.EmailAlreadyUsedException;
import com.example.testniveaujava.reposirories.ClientRepository;
import com.example.testniveaujava.responses.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ClientRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public MessageResponse register(RegisterRequest request) throws EmailAlreadyUsedException {
        if (this.repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyUsedException("Error: Email is already in use!");

        }
        var client= Client.builder()
               .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .build();
        repository.save(client);
        var jwtToken=jwtService.generateToken(client);
            return MessageResponse.builder()
                .token(jwtToken)
                .build();
    }

    public MessageResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user =repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return MessageResponse.builder()
                .token(jwtToken)
                .build();
    }


}

