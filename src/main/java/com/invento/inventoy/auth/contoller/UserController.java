package com.invento.inventoy.auth.contoller;

import com.invento.inventoy.auth.services.UserServices;
import com.invento.inventoy.config.exeption.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userServices;

    @GetMapping("/detail")
    public ResponseEntity<Response.ApiResponse<UserDetails>> UserDetail (Authentication authentication){
        UserDetails userDetails = userServices.loadUserByCurrentUser(authentication);
        Response.ApiResponse<UserDetails> res = Response.ApiResponse.success("Current user fetched", userDetails);
        return ResponseEntity.status(res.status()).body(res);
    };
}
