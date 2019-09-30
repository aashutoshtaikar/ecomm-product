package com.ecommerce.product.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.product.entity.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Mock
	private Product mockProduct;
	
	@Before
	public void setUp() {
		mockProduct = new Product(1L,"cornflakes",101L,3.2,"kroger");
		productRepository.save(mockProduct);
	}
	
	@After
	public void tearDown() {
		productRepository.deleteAll();
	}
	
	@Test
	public void findAll_returnsProducts() throws Exception{
		Iterable<Product> products = productRepository.findAll();
		assertThat(products).isNotEmpty();
		assertThat(products).hasSize(1);
	}
	
	@Test
	public void findById_returnsProduct() throws Exception{
		 Optional<Product> product = productRepository.findById(1L);
		 assertThat(product.get()).isEqualToComparingFieldByField(mockProduct);
	}
	
	@Test
	public void findByName_returnsProduct() throws Exception{
		Product product = productRepository.findByName("cornflakes");
		assertThat(product).isEqualToComparingFieldByField(mockProduct);
	}
	
	@Test
	public void findByCategoryId_returnsAllProducts() throws Exception {
		Iterable<Product> products = productRepository.findByCategoryId(101L);
		assertThat(products).isNotEmpty();
		assertThat(products).hasSize(1);
	}
	
	@Test
	public void save_returnsProduct() throws Exception{
		Product product = productRepository.save(mockProduct);
		assertThat(product).isEqualToComparingFieldByField(mockProduct);
	}
	
	@Test
	public void updateProduct_returnsProduct() {
		Product product = productRepository.save(mockProduct);
		assertThat(product).isEqualToComparingFieldByField(mockProduct);
	}
	
	@Test
	public void deleteProduct() {
		productRepository.deleteById(1L);
		assertThat(productRepository.findAll()).isEmpty();
	}
	
	
}
