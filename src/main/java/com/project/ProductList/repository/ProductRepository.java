package com.project.ProductList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.ProductList.entity.Product;
public interface ProductRepository extends JpaRepository<Product,Integer> {
	
	@Query("select p from Product p where p.productName=:productName")
	public Product findProductByProductName(@Param("productName")String productName);

}
