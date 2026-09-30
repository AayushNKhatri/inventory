package com.invento.inventoy.auth.contoller;

import com.invento.inventoy.auth.dto.AuthDTO;
import com.invento.inventoy.auth.services.AuthServices;
import com.invento.inventoy.config.exeption.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServices authServices;
    @PostMapping("/login")
    public ResponseEntity<Response.ApiResponse<String>> login(@RequestBody AuthDTO.LoginRequest request){
            var res = authServices.Login(request);
            return ResponseEntity.status(res.status()).body(res);
    }
    @PostMapping("/register")
    public ResponseEntity<Response.ApiResponse<Void>> Register(@RequestBody AuthDTO.RegisterRequest request){
        var res = authServices.Register(request);
        return ResponseEntity.status(res.status()).body(res);
    }

}
