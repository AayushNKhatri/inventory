package com.invento.inventoy.AttributeDefination.dto;

import com.invento.inventoy.ProductType.Dto.ProductTypeDto;
import com.invento.inventoy.persistance.entity.ProductType;

public class AttributeDefinitionDto {
    public record CreateAttributeDefinitionDto(String name, String DataType, boolean required) {};
    public record UpdateAttributeDefinitionDto(String name, String DataType, boolean required) {};
    public record ReturnAttributeDefinitionDTO(
            Long Id,
            String name,
            String DataType,
            boolean required,
            ProductTypeDto.ReturnForProductType ProductType
    ) {};
    public record ReturnAttributeDefinition(
            Long Id,
            String name,
            String DataType,
            boolean required
    ) {};
}
