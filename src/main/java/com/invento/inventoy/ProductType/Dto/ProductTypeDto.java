package com.invento.inventoy.ProductType.Dto;

import com.invento.inventoy.AttributeDefination.dto.AttributeDefinitionDto;
import com.invento.inventoy.persistance.entity.AttributeDefinition;

import java.util.List;

public class ProductTypeDto {
    public record CreateProductTypeDto(String name, String description){};
    public record UpdateProductTypeDto(String name, String description){};
    public record ReturnProductTypeDto(
            Long Id,
            String Name,
            String Description,
            List<AttributeDefinitionDto.ReturnAttributeDefinition> AttributeDefination
    ){ };
    public record ReturnForProductType(
            Long Id,
            String Name,
            String Description
    ){}
}
