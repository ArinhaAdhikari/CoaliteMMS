package com.project.ProductList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.ProductList.entity.Product;
import com.project.ProductList.exception.ResourceAlreadyExistException;
import com.project.ProductList.exception.ResourceNotFoundException;
import com.project.ProductList.repository.ProductRepository;
import com.project.ProductList.service.ProductService;

@SpringBootTest(classes = {ProductServiceMockitoTests.class})
public class ProductServiceMockitoTests {
	
	@Mock 
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	private List<Product> products = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		products.add(new Product(1, "Coffee Packet", "Catering", 45, null));
		products.add(new Product(2, "Tea Bag", "Catering", 90, null));
	}
	
	
	@Test
	@Order(1)
	public void test_getProductById() {
		when(productRepository.findById(1)).thenReturn(Optional.of(products.get(0)));
		
		Product productById = productService.getProductById(1);	
		
		assertEquals(productById.toString() ,products.get(0).toString());
	}
	
	
	@Test
	@Order(2)
	public void test_getAllProduct() {
		when(productRepository.findAll()).thenReturn(products);
		
		List<Product> allProduct = productService.getAllProduct();	
		
		assertEquals(allProduct.size(), 2);
	}
	
	@Test

	@Order(3)

	public void test_getProductById_productNotPresent_throwsException() {

	when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

	 

	ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,

	()-> productService.getProductById(1));

	 

	assertEquals("Product is not found for ProductId : 1", resourceNotFoundException.getMessage());

	}

	 

	 

	@Test

	@Order(4)

	public void test_updateProduct() throws Exception{

	Product oldProduct = new Product(1, "tea", "", 0, null);

	 

	Product newProduct = new Product(1, "tea", "Catering", 0, null);

	 

	when(productRepository.findById(1)).thenReturn(Optional.of(oldProduct));

	 

	when(productRepository.save(newProduct)).thenReturn(newProduct);

	 

	Product updateProduct = productService.updateProduct(newProduct, 1);

	 

	assertEquals(updateProduct.getProductId(), 1);

	assertEquals(updateProduct.getProductDept(), "Catering");

	}

	 

	 

	@Test

	@Order(5)

	public void test_updateProduct_productNotPresent_throwsException() {

	when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

	 

	ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,

	()-> productService.updateProduct(new Product(), 1));

	 

	assertEquals("product is not found for productId : 1", resourceNotFoundException.getMessage());

	}

	 

	 

	@Test

	@Order(6)

	public void test_deleteProduct_notPresentThrowsException(){

	when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

	 

	ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,

	()-> productService.deleteProduct(1));

	 

	assertEquals("Product is not found for ProductId : 1", resourceNotFoundException.getMessage());

	}

	 

	 

	@Test

	@Order(7)

	public void test_createProduct() {

	when(productRepository.findProductByProductName(anyString())).thenReturn(null);

	 

	when(productRepository.save(any(Product.class))).thenReturn(new Product(1, "Tea", null, 0, null));

	 

	Product saveProduct = productService.saveProduct(new Product(0, "Tea", null, 0, null));

	 

	assertEquals(saveProduct.getProductId(), 1);

	}

	 

	@Test

	@Order(8)

	public void test_saveProduct_productwithNameAlreadyPresent_throwsException () {

	when(productRepository.findProductByProductName(anyString())).thenReturn(new Product());

	 

	ResourceAlreadyExistException resourceAlreadyExistException = assertThrows(ResourceAlreadyExistException.class,

	()->

	 

	productService.saveProduct(new Product(0, "Tea", null, 0, null)));

	 

	assertEquals("Product is already present with productName : Tea", resourceAlreadyExistException.getMessage());

	}
}
