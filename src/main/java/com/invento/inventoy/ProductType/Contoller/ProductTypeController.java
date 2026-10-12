package com.invento.inventoy.ProductType.Contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invento.inventoy.ProductType.Dto.ProductTypeDto;
import com.invento.inventoy.ProductType.Dto.ProductTypeDto.CreateProductTypeDto;
import com.invento.inventoy.ProductType.Dto.ProductTypeDto.ReturnProductTypeDto;
import com.invento.inventoy.ProductType.service.ProductTypeService;
import com.invento.inventoy.config.exeption.Response;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product-type")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("/create")
    public ResponseEntity<Response.ApiResponse<Void>> Create(@RequestBody CreateProductTypeDto request){
        Response.ApiResponse<Void> res = productTypeService.CreateProductType(request);
        return ResponseEntity.status(res.status()).body(res);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response.ApiResponse<Void>> UpdateProductType(@RequestBody ProductTypeDto.UpdateProductTypeDto request, @PathVariable Long id){
        Response.ApiResponse<Void> res = productTypeService.UpdateProductType(request,id);
        return ResponseEntity.status(res.status()).body(res);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response.ApiResponse<Void>> DeleteProductType(@PathVariable Long id){
        Response.ApiResponse<Void> res = productTypeService.DeleteProductType(id);
        return ResponseEntity.status(res.status()).body(res);
    }
    @GetMapping("/GetAll")
    public ResponseEntity<Response.ApiResponse<List<ReturnProductTypeDto>>> GetAll(){
        Response.ApiResponse<List<ReturnProductTypeDto>> res = productTypeService.GetAllProductTypes();
        return ResponseEntity.status(res.status()).body(res);
    }
    @GetMapping("/Get/{id}")
    public ResponseEntity<Response.ApiResponse<ReturnProductTypeDto>> Get(@PathVariable Long id){
        Response.ApiResponse<ReturnProductTypeDto>  res = productTypeService.GetProductTypeById(id);
        return ResponseEntity.status(res.status()).body(res);
    }
}
