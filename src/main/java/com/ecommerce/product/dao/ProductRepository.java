package com.ecommerce.product.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByName(String name);
	List<Product> findByCategoryId(Long id);
}
