package com.ecommerce.product.service;

import java.util.List;

import com.ecommerce.product.entity.Product;

public interface ProductService {
	List<Product> getAllProducts();
	List<Product> getAllProductsByCategoryId(Long id);
	Product getProductByName(String name);
	Product getProductById(Long id);
	Product addNewProduct(Product product);
	Product updateProduct(Product product);
	void deleteProductById(Long id);
}
