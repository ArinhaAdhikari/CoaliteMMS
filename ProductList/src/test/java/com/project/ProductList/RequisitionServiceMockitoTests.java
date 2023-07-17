package com.project.ProductList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.project.ProductList.entity.Requisition;
import com.project.ProductList.payloads.UserDTO;
import com.project.ProductList.repository.ProductRepository;
import com.project.ProductList.repository.RequisitionRepository;
import com.project.ProductList.service.RequisitionService;

@SpringBootTest(classes = {RequisitionServiceMockitoTests.class})
public class RequisitionServiceMockitoTests {
	
	@Mock
	private RequisitionRepository requisitionRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private ModelMapper mapper;
	
	
	@InjectMocks
	private RequisitionService requisitionService;
	
	
	
	
	
	
	@Test
	public void test_getAllRequisition() {
	
		List<Requisition> requisitions = new ArrayList<>();
		
		requisitions.add(new Requisition(1, 23, 1, "APPROVED", null, null));
		requisitions.add(new Requisition(2, 25, 2, "DECLINED", null, null));
		
		when(restTemplate.getForObject("http://localhost:8083/user/service/user/1", Object.class)).thenReturn(new UserDTO(1, "Aman Kumar", "aman@gmail.com", "Akash@123"));
		when(restTemplate.getForObject("http://localhost:8083/user/service/user/2", Object.class)).thenReturn(null);
//		when(restTemplate.getForObject("http://localhost:8083/user/service/user/1", Object.class)).thenReturn(new UserDTO(1, "Aman Kumar", "aman@gmail.com", "Akash@123"));
		
		when(requisitionRepository.findAll()).thenReturn(requisitions);
		
		when(mapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(new UserDTO(1, "Aman Kumar", "aman@gmail.com", "Akash@123"));
		
		List<Requisition> allRequisition = requisitionService.getAllRequisition();
		
		assertEquals(allRequisition.get(0).getUserid(), 1);
		assertEquals(allRequisition.size(),1);
	}

}
