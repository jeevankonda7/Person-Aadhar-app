package org.olympus.personaadhar.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.olympus.personaadhar.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
	
	@Autowired
	private EntityManager manager;
	
	
	public Person save(Person person) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(person);
		transaction.commit();
		return person;
	}
	
	public Person update(Person person) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(person);
		transaction.commit();
		return person;
	}
	
	public Person findById(int id) {
		Person p = manager.find(Person.class, id);
		if(p!=null) {
			return p;
		}
		return null;
	}
	
	public boolean delete(int id) {
		Person p = manager.find(Person.class, id);
		if(p!=null) {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(p);
			transaction.commit();
			return true;
		}
		return false;
	}
	
	

}
