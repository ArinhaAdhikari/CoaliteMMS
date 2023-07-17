package com.project.ProductList.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ProductList.entity.Requisition;
import com.project.ProductList.service.RequisitionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class RequisitionController {
	@Autowired
	private RequisitionService requisitionService;
	@PostMapping("/requisitions/{productId}/user/{userId}")
	public Requisition saveRequisition(@Valid @RequestBody Requisition requisition,@PathVariable Integer productId,@PathVariable Integer userId)
	{
		return requisitionService.saveRequisition(requisition,productId,userId);
	}
	@GetMapping("/requisitions")
	public List<Requisition> getAllRequisition()
	{
		return requisitionService.getAllRequisition();
	}

	@GetMapping("/requisitions/{id}")
	public Requisition getRequisitionbyId(@PathVariable int id)
	{
		return requisitionService.getRequisitionById(id);
	}
	@GetMapping("/admin/requisitions/approve/{id}")
	public Requisition acceptRequisitionbyAdmin(@PathVariable int id)
	{
		return requisitionService.updateRequisitionbyAdmin(id);
	}
	
	@GetMapping("/admin/requisitions/decline/{requisitionId}")
	public Requisition declineRequisitionbyAdmin(@PathVariable int requisitionId) {
		Requisition declineRequisitionbyAdmin = this.requisitionService.declineRequisitionbyAdmin(requisitionId);
		return declineRequisitionbyAdmin; 
	}
	
	@GetMapping("/requisitions/user/{userId}")
	public List<Requisition> getAllRequisitionByUserId(@PathVariable Integer userId){
		List<Requisition> requisitionByUserId = this.requisitionService.getRequisitionByUserId(userId);
		return requisitionByUserId;
	}

	//delete requisition for given id
	@DeleteMapping("/service/requisitions/{userId}")
	public void deleteRequistionForUserId(@PathVariable Integer userId) {
		
	}
	
	
}
