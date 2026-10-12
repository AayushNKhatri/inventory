package com.invento.inventoy.ProductType.service;

import com.invento.inventoy.AttributeDefination.dto.AttributeDefinitionDto;
import com.invento.inventoy.ProductType.Dto.ProductTypeDto;
import com.invento.inventoy.ProductType.repo.ProductTypeRepo;
import com.invento.inventoy.config.exeption.Response.ApiResponse;
import com.invento.inventoy.persistance.entity.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepo productTypeRepo;

    public ApiResponse<Void> CreateProductType(ProductTypeDto.CreateProductTypeDto request){
        ProductType productType = new ProductType(
                request.name(),
                request.description()
        );
        productTypeRepo.save(productType);
        return ApiResponse.success("Successfully added");
    }
    @Transactional
    public ApiResponse<Void> UpdateProductType(ProductTypeDto.UpdateProductTypeDto request, Long id){
        ProductType productType = productTypeRepo.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NoSuchElementException("Product type with id: " + id + " does not exist"));
        productType.update(request.name(), request.description());
        return ApiResponse.success("Successfully updated");
    }

    @Transactional
    public ApiResponse<Void> DeleteProductType(Long id){
        ProductType productType = productTypeRepo.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NoSuchElementException("Product with id : " + id + "does not exist"));
        productType.deleteProductType();
        return ApiResponse.success("Successfully deleted");
    }
    public ApiResponse<ProductTypeDto.ReturnProductTypeDto> GetProductTypeById(Long id){
        ProductTypeDto.ReturnProductTypeDto productType = productTypeRepo.findByIdAndIsDeletedFalse(id).map(
                x -> new ProductTypeDto.ReturnProductTypeDto(
                        x.getId(),
                        x.getName(),
                        x.getDescription(),
                        x.getAttribute_definition().stream().map(
                                y -> new AttributeDefinitionDto.ReturnAttributeDefinition(
                                        y.getId(),
                                        y.getAttributeDefinitionName(),
                                        y.getAttributeDataType(),
                                        y.isRequeried()
                                )
                        ).toList()
                )
        ).orElseThrow(() -> new NoSuchElementException("Product type with id: " + id + " does not exist"));
        return ApiResponse.success("Successfully retrieved",  productType);
    }
    public ApiResponse<List<ProductTypeDto.ReturnProductTypeDto>> GetAllProductTypes(){
        List<ProductTypeDto.ReturnProductTypeDto> productTypes = productTypeRepo.findAllByIsDeletedFalse()
                .stream()
                .map(x -> new ProductTypeDto.ReturnProductTypeDto(
                        x.getId(),
                        x.getName(),
                        x.getDescription(),
                        x.getAttribute_definition().stream().map(a -> new AttributeDefinitionDto.ReturnAttributeDefinition(
                                a.getId(),
                                a.getAttributeDefinitionName(),
                                a.getAttributeDataType(),
                                a.isRequeried()
                        )).toList()
                        )).toList();
        return ApiResponse.success("Successfully retrieved",  productTypes);
    }

}
