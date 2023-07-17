package com.project.ProductList.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ProductList.entity.Product;
import com.project.ProductList.exception.ResourceAlreadyExistException;
import com.project.ProductList.exception.ResourceNotFoundException;
import com.project.ProductList.repository.ProductRepository;
@Service
public class ProductService implements ProductServiceInterface{
@Autowired
private ProductRepository productRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		 Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","ProductId", id));
		 log.info("Fetched product: {}", product.getProductName());
		 return product;
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findAll();
		log.info("Fetched {} product(s)", products.size());
		return products;
		}
	
	
	

	@Override
	public Product updateProduct( Product newproduct,int id) {
		
		Product product=productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product", "productId",id));
		product.setProductName(newproduct.getProductName());
		product.setProductDept(newproduct.getProductDept());
		product.setProductQuantity(newproduct.getProductQuantity());
		return productRepository.save(product);
	}
	

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","ProductId", id));
		 
		productRepository.delete(product);
		
	}

	@Override
	public Product saveProduct(Product product) {
		String name = product.getProductName();
		Product prod = this.productRepository.findProductByProductName(name);
		if(prod!=null)
			throw new ResourceAlreadyExistException("Product is already present with productName : "+name);
		return this.productRepository.save(product);
	}

}
