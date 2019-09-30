//package com.ecommerce.product;
//
//import java.util.List;
//
//import org.assertj.core.api.Assertions;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.ecommerce.product.entity.Product;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class IntegrationTest {
//	private static String rootRestUrl;
//	
//	@Autowired
//	private TestRestTemplate testRestTemplate;
//	
//	@BeforeClass
//	public static void initTest(@Value("${root.rest.url}") String theRootRestUrl) {
//		rootRestUrl = theRootRestUrl;
//	}
//	
//	@Test
//	public void getAllProducts() {
//		ResponseEntity<List<Product>> responseEntity = testRestTemplate.exchange(rootRestUrl + "/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>(){});
//		
//		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//		Assertions.assertThat(responseEntity.getBody()).isEqualTo(expected)
//	}
//	
//	@Test
//	public void getAllProductsByCategoryId() {
//		
//	}
//	
//	@Test
//	public void getProductByName() {
//		
//	}
//	
//	@Test
//	public void getProductById() {
//		
//	}
//	
//	@Test
//	public void addNewProduct() {
//		
//	}
//	
//	@Test
//	public void updateProduct() {
//		
//	}
//	
//	@Test
//	public void deleteProduct() {
//		
//	}
//}
