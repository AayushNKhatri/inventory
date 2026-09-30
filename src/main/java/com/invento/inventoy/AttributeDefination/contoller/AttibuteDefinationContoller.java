package com.invento.inventoy.AttributeDefination.contoller;


import com.invento.inventoy.AttributeDefination.dto.AttributeDefinitionDto;
import com.invento.inventoy.AttributeDefination.service.AttributeDefinitionService;
import com.invento.inventoy.config.exeption.Response;
import com.invento.inventoy.persistance.entity.AttributeDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attribute-defination")
public class AttibuteDefinationContoller {
    private final AttributeDefinitionService attributeDefinitionService;

    @PostMapping("/create/{productTypeId}")
    public ResponseEntity<Response.ApiResponse> Create(@RequestBody AttributeDefinitionDto.CreateAttributeDefinitionDto createAttributeDefinitionDto, @PathVariable Long productTypeId){
        Response.ApiResponse res = attributeDefinitionService.Create(createAttributeDefinitionDto, productTypeId);
        return ResponseEntity.status(res.status()).body(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response.ApiResponse> Update(@RequestBody AttributeDefinitionDto.UpdateAttributeDefinitionDto updateAttributeDefinitionDto, @PathVariable Long id){
        Response.ApiResponse res = attributeDefinitionService.Update(updateAttributeDefinitionDto, id);
        return ResponseEntity.status(res.status()).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response.ApiResponse> Get(@PathVariable Long id){
        Response.ApiResponse<AttributeDefinitionDto.ReturnAttributeDefinitionDTO> res = attributeDefinitionService.GetAttributeDefinitionById(id);
        return ResponseEntity.status(res.status()).body(res);
    }
    @GetMapping("/all")
    public ResponseEntity<Response.ApiResponse> GetAll(){
        Response.ApiResponse<List<AttributeDefinitionDto.ReturnAttributeDefinitionDTO>> res = attributeDefinitionService.GetAllAttributeDefinitions();
        return ResponseEntity.status(res.status()).body(res);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response.ApiResponse> Delete(@PathVariable Long id){
        Response.ApiResponse res = attributeDefinitionService.Delete(id);
        return ResponseEntity.status(res.status()).body(res);
    }
}
