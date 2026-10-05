package com.invento.inventoy.product.controller;

import com.invento.inventoy.config.exeption.Response;
import com.invento.inventoy.persistance.entity.Product;
import com.invento.inventoy.product.dto.ProductDto;
import com.invento.inventoy.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Response.ApiResponse> Create (@RequestBody ProductDto.CreateProduct createProduct) {
        Response.ApiResponse res = productService.Create(createProduct);
        return ResponseEntity.status(res.status()).body(res);
    }
}
