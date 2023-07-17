package com.project.ProductList.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.ProductList.entity.Requisition;

public interface RequisitionRepository extends JpaRepository<Requisition,Integer> {

	@Query("select r from Requisition r where r.userid=:id")
	public List<Requisition> getRequisitionsByUserId(@Param("id")int id); 
	
	
}
