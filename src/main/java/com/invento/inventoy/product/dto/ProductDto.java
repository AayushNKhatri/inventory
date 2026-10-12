package com.invento.inventoy.product.dto;

import java.math.BigDecimal;

public class ProductDto {
    public record CreateProduct(String name, String description, Long productTypeId, BigDecimal purchasePrice, BigDecimal sellingPrice, int sellableStock, int damagedStock) {};
    public record UpdateProduct(String name, String description, Long productTypeId, BigDecimal purchasePrice, BigDecimal sellingPrice) {};
    public record ViewProduct(
            Long Id,
        String name,
        String description,
        Long productTypeId,
        String ProductType,
        BigDecimal purchasePrice,
        BigDecimal sellingPrice,
        int sellableStock,
        int damagedStock
    ){};
}
