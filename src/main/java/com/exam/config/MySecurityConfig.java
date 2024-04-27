package com.exam.config;

import com.exam.Repository.UserRepository;

import com.exam.service.serviceimpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Configuration

public class MySecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private UserRepository userRepository;




    //Spring Security class to proceed with login.
    //Uses a spring security pre-built entity-User to store all the details that will be used while login


//
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }


//     password Encoder method
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//    }



    @Bean
         protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
             http
                     .csrf(csrf -> csrf
                             .disable())
                     .cors(cors -> cors
                             .disable())
                     .authorizeHttpRequests(requests -> requests
                             .requestMatchers("generate-token", "/user/").permitAll()
                             .requestMatchers(HttpMethod.OPTIONS).permitAll()
                             .anyRequest().authenticated())
                     .exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
                     .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

             http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);


             return http.build();
     }

}
