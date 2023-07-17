package com.project.ProductList;



	
	import static org.mockito.Mockito.when;

	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

	 

	import java.util.List;

	 

	import org.junit.jupiter.api.Order;

	import org.junit.jupiter.api.Test;

	import org.springframework.beans.factory.annotation.Autowired;

	import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

	import org.springframework.boot.test.mock.mockito.MockBean;

	import org.springframework.http.MediaType;

	import org.springframework.test.web.servlet.MockMvc;

	 

	import com.fasterxml.jackson.databind.ObjectMapper;

	import com.project.ProductList.controller.ProductController;

	import com.project.ProductList.entity.Product;

	import com.project.ProductList.service.ProductService;

	 

	@WebMvcTest({ProductController.class})

	public class ProductControllerTests {
	
	 

	@MockBean

	ProductService productService;

	 

	@Autowired

	MockMvc mockMvc;

	 

	 

	@Test

	@Order(1)

	public void test_getAllProducts() throws Exception {

	List<Product> products = List.of(new Product(1, "Tea", null, 0, null),new Product(2, "Coffee", null, 0, null));

	 

	when(productService.getAllProduct()).thenReturn(products);

	 

	mockMvc.perform(get("/products/getproducts").contentType(MediaType.APPLICATION_JSON)).andExpect(result->{result.equals(products);});

	 

	}

	 

	@Test

	@Order(2)

	public void test_saveProduct() throws Exception {

	Product product = new Product(1, "Tea", null, 0, null);

	 

	when(productService.saveProduct(product)).thenReturn(product);

	 

	mockMvc.perform(post("/products/postproducts",product).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(product)))

	.andExpect(result->{result.equals(product);});

	}

	 

	 

	
	
}
