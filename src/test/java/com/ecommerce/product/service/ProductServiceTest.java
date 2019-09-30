package com.ecommerce.product.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ecommerce.product.dao.ProductRepository;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.ProductNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService = new ProductServiceImpl();
	
	private Product mockProduct = null;
	
	@Before
	public void setUp() {
		 mockProduct = new Product(1L,"cornflakes",101L,3.2,"kroger");
	}
	
	@Test
	public void getAllProducts_returnsProducts() throws Exception {
		given(productRepository.findAll()).willReturn(Collections.emptyList());
		assertThat(productService.getAllProducts()).isEmpty();
		verify(productRepository).findAll();
	}
	
	@Test
	public void getProductbyId_returnsProduct() throws Exception{
		given(productRepository.findById(1L)).willReturn(Optional.of(mockProduct));	
		Product product = productService.getProductById(1L);
		assertThat(product).isEqualTo(mockProduct);
	}
	
	@Test
	public void getProductByName_returnsProduct() throws Exception{
		given(productRepository.findByName("cornflakes")).willReturn(mockProduct);
		Product product = productService.getProductByName("cornflakes");
		assertThat(product).isEqualTo(mockProduct);
	}
	
	@Test
	public void getProductsByCategoryId_returnsProducts() throws Exception{
		given(productRepository.findByCategoryId(1L)).willReturn(Collections.emptyList());
		List<Product> products = productService.getAllProductsByCategoryId(1L);
		assertThat(products).isEqualTo(Collections.emptyList());
	}
	
	@Test
	public void updateProduct_returnsProduct() throws Exception{
		given(productRepository.save(mockProduct)).willReturn(mockProduct);
		Product product = productService.updateProduct(mockProduct);
		assertThat(product).isEqualTo(mockProduct);
	}
	
	@Test
	public void deleteProduct_deletesProduct() throws Exception{
		productService.deleteProductById(1L);
		verify(productRepository).deleteById(1L);
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void getProductById_throwsNotFoundException() throws Exception{
		given(productRepository.findById(any())).willReturn(Optional.empty());	
		productService.getProductById(1L);
		verify(productRepository).findById(1L);
	}
	
}
