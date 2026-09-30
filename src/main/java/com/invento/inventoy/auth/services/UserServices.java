package com.invento.inventoy.auth.services;
import com.invento.inventoy.auth.repo.UserRepository;
import com.invento.inventoy.persistance.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServices implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found" + username));
        return User.withUsername(user.getUsername()).password(user.getPasswordHash()).build();
    }

    public UserDetails loadUserByCurrentUser(Authentication authentication){
        String username = authentication.getName();
        UserEntity user = userRepo.findByUsername(username).orElseThrow( () -> new NoSuchElementException("Username not found"));
        return User.withUsername(user.getUsername()).build();
    }
}
