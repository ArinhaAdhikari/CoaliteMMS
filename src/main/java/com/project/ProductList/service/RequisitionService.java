package com.project.ProductList.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.ProductList.entity.Product;
import com.project.ProductList.entity.Requisition;
import com.project.ProductList.exception.ResourceNotFoundException;
import com.project.ProductList.payloads.UserDTO;
import com.project.ProductList.repository.ProductRepository;
import com.project.ProductList.repository.RequisitionRepository;
@Service
public class RequisitionService implements RequisitionServiceInterface {

	@Autowired
	private RequisitionRepository requisitionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Requisition saveRequisition(Requisition requisition,Integer productId,Integer userId) {
		Object userObject = this.restTemplate.getForObject("http://localhost:8083/user/service/user/"+userId, Object.class);
		if(userObject==null)
			throw new ResourceNotFoundException("User", "userId", userId);
		
				
		// TODO Auto-generated method stub
		Product product = this.productRepository.findById(productId).orElse(null);
		if(product==null)
			throw new ResourceNotFoundException("Product", "productId", productId);
//		product.getRequisitions().add(requisition);
		requisition.setProduct(product);
		requisition.setUserid(userId);	
		
		UserDTO user = this.modelMapper.map(userObject, UserDTO.class);
		
		
		requisition.setUser(user);
		
		
		productRepository.save(product);
		return requisitionRepository.save(requisition);
	}

	@Override
	public Requisition getRequisitionById(int id) {
		// TODO Auto-generated method stub
		return requisitionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Requisition","RequisitionId", id)) ;
	}
	
	

	@Override
	public List<Requisition> getAllRequisition() {
		// TODO Auto-generated method stub
		List<Requisition> requisitions = requisitionRepository.findAll();
		
		for(int index=0;index<requisitions.size();index++) {
//			System.out.println(req.getUserid());
			Requisition req = requisitions.get(index);
			Object userObject = this.restTemplate.getForObject("http://localhost:8083/user/service/user/"+req.getUserid(), Object.class);
			if(userObject==null) {
				requisitions.remove(index);
				continue;
			}
			UserDTO user = this.modelMapper.map(userObject, UserDTO.class);
			req.setUser(user);
		}
		return requisitions;
	}
	
	
	

	@Override
	public Requisition updateRequisitionbyAdmin(int id) {
		Requisition requisition = this.requisitionRepository.findById(id).get();
		Product product = requisition.getProduct();
		if(product.getProductQuantity() < requisition.getRequisitionQuantity())
			return null;
		int quantity = product.getProductQuantity() - requisition.getRequisitionQuantity();
		product.setProductQuantity(quantity);
		requisition.setStatus("APPROVED");
		this.productRepository.save(product);
		Requisition save = this.requisitionRepository.save(requisition);	
		return save;
	}

	@Override
	public List<Requisition> getRequisitionByUserId(int id) {
		// TODO Auto-generated method stub
		Object userObject = this.restTemplate.getForObject("http://localhost:8083/user/service/user/"+id, Object.class);
		if(userObject==null)
			throw new ResourceNotFoundException("User", "userId", id);
		
		
		UserDTO user = this.modelMapper.map(userObject, UserDTO.class);
		
		
		
		List<Requisition> requisitions = requisitionRepository.getRequisitionsByUserId(id);
		
		for(Requisition req : requisitions) {
			req.setUser(user);
		}
		
		return requisitions;
	}

	@Override
	public Requisition declineRequisitionbyAdmin(int id) {
		Requisition requisition = this.requisitionRepository.findById(id).get();
		requisition.setStatus("DECLINED");
		Requisition save = this.requisitionRepository.save(requisition);
		return save;
	}
	

}
