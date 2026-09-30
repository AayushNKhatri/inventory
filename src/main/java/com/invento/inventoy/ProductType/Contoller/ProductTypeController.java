package com.invento.inventoy.ProductType.Contoller;

import com.invento.inventoy.ProductType.Dto.ProductTypeDto;
import com.invento.inventoy.ProductType.Dto.ProductTypeDto.CreateProductTypeDto;
import com.invento.inventoy.ProductType.service.ProductTypeService;
import com.invento.inventoy.config.exeption.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product-type")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("/create")
    public ResponseEntity<Response.ApiResponse> Create(@RequestBody CreateProductTypeDto request){
        Response.ApiResponse res = productTypeService.CreateProductType(request);
        return ResponseEntity.status(res.status()).body(res);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response.ApiResponse> UpdateProductType(@RequestBody ProductTypeDto.UpdateProductTypeDto request, @PathVariable Long id){
        Response.ApiResponse res = productTypeService.UpdateProductType(request,id);
        return ResponseEntity.status(res.status()).body(res);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response.ApiResponse> DeleteProductType(@PathVariable Long id){
        Response.ApiResponse res = productTypeService.DeleteProductType(id);
        return ResponseEntity.status(res.status()).body(res);
    }
    @GetMapping("/GetAll")
    public ResponseEntity<Response.ApiResponse> GetAll(){
        Response.ApiResponse res = productTypeService.GetAllProductTypes();
        return ResponseEntity.status(res.status()).body(res);
    }
    @GetMapping("/Get/{id}")
    public ResponseEntity<Response.ApiResponse> Get(@PathVariable Long id){
        Response.ApiResponse res = productTypeService.GetProductTypeById(id);
        return ResponseEntity.status(res.status()).body(res);
    }
}
