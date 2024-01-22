package org.olympus.personaadhar.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.olympus.personaadhar.dto.Aadhar;
import org.olympus.personaadhar.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AadharDao {

	@Autowired
	private EntityManager manager;

	public Aadhar save(Aadhar aadhar, int p_id) {
		Person p = manager.find(Person.class, p_id);
		if (p != null) {
			aadhar.setPerson(p);
			p.setAadhar(aadhar);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(aadhar);
			transaction.commit();
			return aadhar;
		}
		return null;

	}

	public Aadhar update(Aadhar aadhar) {
		Aadhar a = manager.find(Aadhar.class, aadhar.getId());
		if (a != null) {
			a.setGender(aadhar.getGender());
			a.setId(aadhar.getId());
			a.setUid(aadhar.getUid());
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(a);
			transaction.commit();
			return a;
		}
		return null;

	}

	public Aadhar findById(int id) {
		Aadhar a = manager.find(Aadhar.class, id);
		if (a != null) {
			return a;
		}
		return null;
	}

	public boolean delete(int id) {
		Aadhar a = manager.find(Aadhar.class, id);
		if (a != null) {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(a);
			transaction.commit();
			return true;
		}
		return false;
	}

}
