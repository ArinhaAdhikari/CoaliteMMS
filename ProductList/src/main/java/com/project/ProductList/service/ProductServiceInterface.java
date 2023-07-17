package com.project.ProductList.service;

import java.util.List;
import java.util.Optional;

import com.project.ProductList.entity.Product;



public interface ProductServiceInterface {
	public Product saveProduct(Product product);
	public Product getProductById(int id);
	List<Product> getAllProduct();
	Product updateProduct(Product product,int id);
	void deleteProduct(int id);


}
