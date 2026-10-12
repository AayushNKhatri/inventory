package com.invento.inventoy.auth.services;

import com.invento.inventoy.auth.dto.AuthDTO.RegisterRequest;
import com.invento.inventoy.auth.dto.AuthDTO.LoginRequest;
import com.invento.inventoy.auth.repo.UserRepository;
import com.invento.inventoy.config.JwtServices;
import com.invento.inventoy.config.exeption.Response;
import com.invento.inventoy.persistance.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServices {
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtServices;
    public Response.ApiResponse<String> Login(LoginRequest loginRequest){
        try{
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            Authentication authenticated = authManager.authenticate(authentication);
            UserDetails user = (UserDetails) authenticated.getPrincipal();
            var token = jwtServices.generateToken(user);
            Response.ApiResponse<String> response = Response.ApiResponse.success("Login Successfully", token);
            return response;
        }
        catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new IllegalStateException("Incorrect username or password");
        } catch (AuthenticationException e) {
        throw new IllegalStateException("Authentication failed: " + e.getMessage());
        }
    }
    public Response.ApiResponse<Void> Register(RegisterRequest registerRequest){
         if(userRepo.existsByUsername(registerRequest.username())){
             throw new IllegalArgumentException("Username already exist");
            }
            if(userRepo.existsByEmail(registerRequest.email())){
                throw new IllegalArgumentException("Email already exist");
            }
            String passwordHash = passwordEncoder.encode(registerRequest.password());
            UserEntity user = new UserEntity(
                    registerRequest.username(),
                    registerRequest.email(),
                    passwordHash
            );
            userRepo.save(user);
            return Response.ApiResponse.success("User Successfully Register");

    }
}
