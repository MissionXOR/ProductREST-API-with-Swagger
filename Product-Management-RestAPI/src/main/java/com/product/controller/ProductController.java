package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.ProductEntity;
import com.product.entityDTO.ProductDTO;
import com.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")

public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	@Operation(summary = "Get All Products")
	public List<ProductEntity> getAllproduct() {
		
		return service.getAllProducts();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get a Product by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Product Found"),
			@ApiResponse(responseCode = "404",description = "Product Not Found")
			})
	public ProductEntity getProductbyid(@PathVariable int id) {
		return service.getProductByID(id);
	}
	
	
	@PostMapping
	@Operation(summary = "Created a new Product")
	public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductDTO dto) {
		
	    return new ResponseEntity<>(service.createProduct(dto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Updated a Product")
	public ProductEntity updateProduct(@PathVariable int id,@Valid @RequestBody ProductDTO dto)
	{
		return service.updateProduct(id, dto);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a Product")
	public void deleteProduct(@PathVariable int id ) {
		service.deleProduct(id);
	}
}

