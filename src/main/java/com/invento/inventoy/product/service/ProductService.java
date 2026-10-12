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
    public ApiResponse<Void> Create(ProductDto.CreateProduct createProduct){
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

    @Transactional
    public ApiResponse<Void> Update(ProductDto.UpdateProduct updateProduct, Long Id){
        Product product = productRepo.findById(Id).orElseThrow(() -> new NoSuchElementException("Product Not Found"));
        Long InventoryId = product.getInventory().getId();
        Inventory inventory = inventoryRepo.findByIdAndIsDeletedFalseAndIsActiveTrue(InventoryId);
        ProductType productType = productTypeRepo.findByIdAndIsDeletedFalse(updateProduct.productTypeId()).orElseThrow(() -> new NoSuchElementException("Product Type Not Found"));
        product.UpdateProduct(updateProduct.name(), updateProduct.description(), productType);
        productRepo.save(product);
        inventory.UpdateInventory(product, updateProduct.purchasePrice(), updateProduct.sellingPrice());
        inventoryRepo.save(inventory);
        return ApiResponse.success("Sucesfully updated");
    }
}
