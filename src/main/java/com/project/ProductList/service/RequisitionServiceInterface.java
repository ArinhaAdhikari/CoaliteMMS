package com.project.ProductList.service;

import java.util.List;
import java.util.Optional;

import com.project.ProductList.entity.Requisition;

public interface RequisitionServiceInterface {
	public Requisition saveRequisition(Requisition requisition,Integer postId,Integer userId);
	
	public Requisition getRequisitionById(int id);// for priority approval
	List <Requisition> getAllRequisition();
	public List<Requisition> getRequisitionByUserId(int id);
	Requisition updateRequisitionbyAdmin(int id);
	Requisition declineRequisitionbyAdmin(int id);
	
	



}
