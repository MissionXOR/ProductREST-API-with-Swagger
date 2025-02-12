package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entity.ProductEntity;
import com.product.entityDTO.ProductDTO;
import com.product.exception.ProductNotFoundException;
import com.product.reposiotry.ProductReposotory;

@Service
public class ProductService {

	@Autowired
	private ProductReposotory reposotory;
	
	public List<ProductEntity> getAllProducts() {
		return reposotory.findAll();
	}
	
	public ProductEntity getProductByID(int id) {
		return reposotory.findById(id)
				.orElseThrow(()-> new ProductNotFoundException("Product Not Found on"+id));
	}
	
	public ProductEntity createProduct(ProductDTO dto) {
		
		ProductEntity product=new ProductEntity();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		
		return reposotory.save(product);
	}
	public ProductEntity updateProduct(int id, ProductDTO dto) {
		
		ProductEntity product=getProductByID(id);
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		
		return reposotory.save(product);
	}
	
	public void deleProduct(int id) {
		
		if (!reposotory.existsById(id))
		{
			throw new ProductNotFoundException("Product with ID " + id + " not found.");
		}
		 reposotory.deleteById(id);
	}
}
