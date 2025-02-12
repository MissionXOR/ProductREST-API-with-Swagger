package com.product.entityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import lombok.Data;


@Data

public class ProductDTO {

	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	@Positive(message = "price must be positive")
	private int price ;
}
