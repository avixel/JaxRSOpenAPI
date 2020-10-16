package fr.istic.taa_tp2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Dao {
	
	private EntityManager manager;

	public Dao(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<String> selectNames(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		// assuming a is an Integer  
		// if returning multiple fields, look into using a Tuple 
//		    or specifying the return type as an Object or Object[]
		CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
		Root<Employee> from = query.from(Employee.class);
		query.multiselect(from.get("name"));

		TypedQuery<String> q = manager.createQuery(query);
		return q.getResultList();
		
	}

}
