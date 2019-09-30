package com.ecommerce.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.ProductAlreadyExistsException;
import com.ecommerce.product.exception.ProductNotFoundException;
import com.ecommerce.product.service.ProductService;

/**
 * Rest controller for products resource
 * @author ataikar
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Get all products
	 * @return A list of products in format of {@link Iterable<Product>}
	 */
	@GetMapping
	public ResponseEntity<Iterable<Product>> getAllProducts(){
		return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	/**
	 * Get products by id
	 * @param id existing id of a product 
	 * @return the result of type {@link Product}
	 * @throws ProductNotFoundException when no product is found matching the id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
	}
	
	/**
	 * Get a product by name
	 * @param name existing name of a product
	 * @return the result of type {@link Product}
	 * @throws ProductNotFoundException when no product is found matching the name
	 */
	@GetMapping("/name/{name}")
	public Product getProductByName(@PathVariable String name) {
		return productService.getProductByName(name);
	}
	
	/**
	 * Get products by category id
	 * @param id
	 * @return the result of type {@link Iterable<Product>}
	 * TODO @throws ProductNotFoundException    
	 */
	@GetMapping("/category/{id}")
	public ResponseEntity<Iterable<Product>> getProductByCategoryId(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getAllProductsByCategoryId(id), HttpStatus.OK);
	}
	
	/**
	 * Add a product
	 * @param product
	 * @return the result of type {@link ResponseEntity}
	 * TODO @throws ProductCreationFailure	
	 */
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product addedProduct = productService.addNewProduct(product);
		return new ResponseEntity<Product>(addedProduct,HttpStatus.CREATED);
	}
	
	/**
	 * update a product
	 * @param product
	 */
	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		return ResponseEntity.ok("resource saved");
	}
	
	/**
	 * Delete a product by id
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
		return ResponseEntity.ok("resource deleted");
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void productNotFoundExceptionHandler(ProductNotFoundException e) {
		// TODO impl
	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public void productAlreadyExistsExceptionHandler(ProductAlreadyExistsException e) {
		// TODO impl
	}
	
}
