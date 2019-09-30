package com.ecommerce.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Test class for testing Behavior of RestController 
 * @author ataikar
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private ProductService productService;
	
	
	@Test
	public void getAllProducts_returnsProducts() throws Exception {
		BDDMockito.given(productService.getAllProducts()).willReturn(Collections.emptyList());
		mockMvc.perform(MockMvcRequestBuilders.get("/products"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray());
		
	}
	
	@Test
	public void getProductbyId_returnsProduct() throws Exception{
		BDDMockito.given(productService.getProductById(ArgumentMatchers.anyLong())).willReturn(new Product(1L,"cornflakes",101L,3.2,"kroger"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("id").value(1L))
			.andExpect(jsonPath("name").value("cornflakes"))
			.andExpect(jsonPath("categoryId").value(101L))
			.andExpect(jsonPath("price").value(3.2))
			.andExpect(jsonPath("seller").value("kroger"));

	}
	
	@Test
	public void getProductByName_returnsProduct() throws Exception{
		BDDMockito.given(productService.getProductByName("cornflakes")).willReturn(new Product(1L,"cornflakes",101L,3.2,"kroger"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/products/name/cornflakes"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("id").value(1L))
			.andExpect(jsonPath("name").value("cornflakes"))
			.andExpect(jsonPath("categoryId").value(101L))
			.andExpect(jsonPath("price").value(3.2))
			.andExpect(jsonPath("seller").value("kroger"));

	}
	
	@Test
	public void getProductsByCategoryId_returnsProducts() throws Exception{
		BDDMockito.given(productService.getAllProductsByCategoryId(ArgumentMatchers.anyLong())).willReturn(Collections.emptyList());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/products/category/101"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void addProduct_returnsProduct() throws Exception{
		BDDMockito.given(productService.addNewProduct(new Product(1L,"cornflakes",101L,3.2,"kroger"))).willReturn(new Product(1L,"cornflakes",101L,3.2,"kroger"));
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/products")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(mapper.writeValueAsString(new Product(1L,"cornflakes",101L,3.2,"kroger"))))
			.andExpect(status().isCreated());
			
	}
	
	@Test
	public void updateProduct_returnsProduct() throws Exception{
		BDDMockito.given(productService.addNewProduct(new Product(1L,"cornflakes",101L,3.2,"kroger"))).willReturn(new Product(1L,"cornflakes",101L,3.2,"kroger"));
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.put("/products")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(mapper.writeValueAsString(new Product(1L,"cornflakes",101L,3.2,"kroger"))))
			.andExpect(status().isOk());
	}
	
	@Test
	public void deleteProduct_returnsProduct() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
			.andExpect(status().isOk());
	}
	
	
}
