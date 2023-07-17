package com.project.ProductList.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ProductList.entity.Product;
import com.project.ProductList.service.ProductService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/products")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;

	@GetMapping("/getproducts")
	public List<Product> getAllProduct()
	{
		System.out.println("Here in /getproducts");
		return productService.getAllProduct();
	}

	
	@PostMapping("/postproducts")
	public Product saveProduct(@Valid @RequestBody Product product) 
	{
		return productService.saveProduct(product);
	}


	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable int id)
	{
		logger.debug("The requested productId is : {}",id);
		return productService.getProductById(id);
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable int id)
	{ 
		Product updatedProduct = this.productService.updateProduct(product,id);
		logger.info("product updated. updated value is{}",product);
		return updatedProduct;
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id)
	{
		productService.deleteProduct(id);
	}
	
	


}

