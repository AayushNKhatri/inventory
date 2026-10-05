package com.invento.inventoy.product.service;

import com.invento.inventoy.ProductType.repo.ProductTypeRepo;
import com.invento.inventoy.config.exeption.Response.ApiResponse;
import com.invento.inventoy.persistance.entity.Inventory;
import com.invento.inventoy.persistance.entity.Product;
import com.invento.inventoy.persistance.entity.ProductType;
import com.invento.inventoy.product.dto.ProductDto;
import com.invento.inventoy.product.repo.InventoryRepo;
import com.invento.inventoy.product.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final InventoryRepo inventoryRepo;
    private final ProductTypeRepo productTypeRepo;

    @Transactional
    public ApiResponse Create(ProductDto.CreateProduct createProduct){
        ProductType productType = productTypeRepo.findByIdAndIsDeletedFalse(createProduct.productTypeId()).orElseThrow(() -> new NoSuchElementException("Product Type Not Found"));
        Product product = new Product(
                createProduct.name(),
                createProduct.description(),
                productType
        );
        productRepo.save(product);
        Inventory inventory = new Inventory(
                product,
                createProduct.damagedStock(),
                createProduct.sellableStock(),
                createProduct.purchasePrice(),
                createProduct.sellingPrice()
        );
        inventoryRepo.save(inventory);
        return ApiResponse.success("Successfully added");
    }
}
