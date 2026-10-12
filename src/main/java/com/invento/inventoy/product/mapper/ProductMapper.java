package com.invento.inventoy.product.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.invento.inventoy.persistance.entity.Product;
import com.invento.inventoy.product.dto.ProductDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	@Mapping(target = "productTypeId", source = "product_type.id")
	@Mapping(target = "ProductType", source = "product_type.name")
	@Mapping(target = "purchasePrice", source = "inventory.purchase_price")
	@Mapping(target = "sellingPrice", source = "inventory.selling_price")
	@Mapping(target = "sellableStock", source = "inventory.sellable_stock")
	@Mapping(target = "damagedStock", source = "inventory.damaged_stock")
	@Mapping(target = "name", source = "product_name")
	@Mapping(target = "description", source = "product_description")
	@Mapping(target = "Id", source = "id")
	ProductDto.ViewProduct toDto(Product entity);
	List<ProductDto.ViewProduct> toDtoList(List<Product> entity);

}
