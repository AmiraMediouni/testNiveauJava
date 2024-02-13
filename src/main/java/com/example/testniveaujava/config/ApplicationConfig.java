package com.example.testniveaujava.config;

import com.example.testniveaujava.reposirories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final ClientRepository repository;
    @Bean
    public UserDetailsService clientDetailsService(){
        return username -> repository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Client not found"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(clientDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
