package com.ecommerce.product.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product.dao.ProductRepository;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public List<Product> getAllProductsByCategoryId(Long id) {
		return productRepository.findByCategoryId(id);
	}

	@Override
	@Transactional
	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	@Transactional
	public Product getProductById(Long id) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id); 
		if (!optionalProduct.isPresent()) {
			throw new ProductNotFoundException();
		}
		return optionalProduct.get();
	}

	@Override
	@Transactional
	public Product addNewProduct(Product product) {
		Product addedProduct = productRepository.save(product);
		return addedProduct;
	}

	@Override
	@Transactional
	public Product updateProduct(Product product) {
		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	@Override
	@Transactional
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}

}
