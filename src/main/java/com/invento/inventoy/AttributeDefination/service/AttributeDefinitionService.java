package com.invento.inventoy.AttributeDefination.service;

import com.invento.inventoy.AttributeDefination.dto.AttributeDefinitionDto;
import com.invento.inventoy.AttributeDefination.repo.AttributeDefinitionRepo;
import com.invento.inventoy.ProductType.Dto.ProductTypeDto;
import com.invento.inventoy.ProductType.repo.ProductTypeRepo;
import com.invento.inventoy.config.exeption.Response.ApiResponse;
import com.invento.inventoy.persistance.entity.AttributeDefinition;
import com.invento.inventoy.persistance.entity.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AttributeDefinitionService {
    private final AttributeDefinitionRepo attributeDefinitionRepo;
    private final ProductTypeRepo productTypeRepo;

    public ApiResponse<Void> Create(AttributeDefinitionDto.CreateAttributeDefinitionDto dto, Long productTypeId){
        ProductType productType = productTypeRepo.findByIdAndIsDeletedFalse(productTypeId).orElseThrow(() ->  new NoSuchElementException("Product with id : " + productTypeId + "does not exist"));
        AttributeDefinition attributeDefinition = new AttributeDefinition(
                dto.name(),
                dto.DataType(),
                dto.required(),
                productType
        );
        attributeDefinitionRepo.save(attributeDefinition);
        return ApiResponse.success("Successfully added");
    }

    @Transactional
    public ApiResponse<Void> Update(AttributeDefinitionDto.UpdateAttributeDefinitionDto dto, Long id){
        AttributeDefinition attributeDefinition = attributeDefinitionRepo.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NoSuchElementException("Attribute definition with id " + id + " does not exist"));
        attributeDefinition.Update(dto.name(), dto.DataType(), dto.required());
        attributeDefinitionRepo.save(attributeDefinition);
        return ApiResponse.success("Successfully Updated");
    }
    @Transactional
    public ApiResponse<Void> Delete(Long id){
        AttributeDefinition attributeDefinition = attributeDefinitionRepo.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new NoSuchElementException("Attribute definition with id " + id + " does not exist"));
        attributeDefinition.deleteUser();
        return  ApiResponse.success("Successfully Deleted");
    }

    public ApiResponse<AttributeDefinitionDto.ReturnAttributeDefinitionDTO> GetAttributeDefinitionById(Long id){
        AttributeDefinitionDto.ReturnAttributeDefinitionDTO attributeDefinition = attributeDefinitionRepo.findByIdAndIsDeletedFalse(id).map(
                x -> new AttributeDefinitionDto.ReturnAttributeDefinitionDTO(
                        x.getId(),
                        x.getAttributeDefinitionName(),
                        x.getAttributeDataType(),
                        x.isRequeried(),
                        new ProductTypeDto.ReturnForProductType(
                                x.getProduct_type().getId(),
                                x.getProduct_type().getName(),
                                x.getProduct_type().getDescription()
                        )
                )
        ).orElseThrow(() -> new NoSuchElementException("Attribute definition with id " + id + " does not exist"));
        return ApiResponse.success("Successfully retrieved attribute definition with id " + id, attributeDefinition);
    }

    public ApiResponse<List<AttributeDefinitionDto.ReturnAttributeDefinitionDTO>> GetAllAttributeDefinitions()
    {
        List<AttributeDefinitionDto.ReturnAttributeDefinitionDTO> attributeDefinitions = attributeDefinitionRepo.findAllByIsDeletedFalse().stream().map(
                x -> new AttributeDefinitionDto.ReturnAttributeDefinitionDTO(
                        x.getId(),
                        x.getAttributeDefinitionName(),
                        x.getAttributeDataType(),
                        x.isRequeried(),
                        new ProductTypeDto.ReturnForProductType(
                                x.getProduct_type().getId(),
                                x.getProduct_type().getName(),
                                x.getProduct_type().getDescription()
                        )


                )
        ).toList();
        return  ApiResponse.success("Successfully retrieved all attribute definitions", attributeDefinitions);
    }
}
