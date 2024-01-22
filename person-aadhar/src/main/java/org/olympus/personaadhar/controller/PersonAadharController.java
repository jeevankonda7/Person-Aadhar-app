package org.olympus.personaadhar.controller;

import java.time.LocalDate;
import java.util.Scanner;

import org.olympus.personaadhar.PersonAadharConfig;
import org.olympus.personaadhar.dao.AadharDao;
import org.olympus.personaadhar.dao.PersonDao;
import org.olympus.personaadhar.dto.Aadhar;
import org.olympus.personaadhar.dto.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PersonAadharController {

	static Scanner sc = new Scanner(System.in);
	static PersonDao pDao;
	static AadharDao aDao;
	static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(PersonAadharConfig.class);
		pDao = context.getBean(PersonDao.class);
		aDao = context.getBean(AadharDao.class);
		System.out.println("select any of the choice:");
		System.out.println("1.enroll the person");
		System.out.println("2.update person details");
		System.out.println("3.find the person by id");
		System.out.println("4.delete the person by id");
		System.out.println("5.add aadhar for person");
		System.out.println("6.update the aadhar details of person");
		System.out.println("7.find aadhar by id");
		System.out.println("8.delete aadhar by id");
		switch (sc.nextInt()) {
		case 1:
			savePerson();
			break;
		case 2:
			updatePerson();
			break;
		case 3:
			findById();
			break;
		case 4:
			deleteById();
			break;
		case 5:
			saveAadhar();
			break;
		case 6:
			updateAadhar();
			break;
		case 7:
			findAadharById();
			break;
		case 8:
			deleteAadharById();
			break;

		default: {
			System.out.println("thank you application closed!!!");
			System.out.println("invalid option choosen");
			((AnnotationConfigApplicationContext) context).close();
			break;
		}
		}
	}

	public static void savePerson() {
		System.out.println("enter the name and phone number of person");
		Person person = new Person();
		person.setName(sc.next());
		person.setPhone(sc.nextLong());
		person = pDao.save(person);
		System.out.println("person saved with id:" + person.getId());
	}

	public static void updatePerson() {
		System.out.println("enter the id, name and phone number of person");
		Person person = new Person();
		person.setId(sc.nextInt());
		person.setName(sc.next());
		person.setPhone(sc.nextLong());
		person = pDao.update(person);
		System.out.println("person updated with id:" + person.getId());
	}

	public static void findById() {
		System.out.println("enter the id of person you want to fetch");
		Person p = pDao.findById(sc.nextInt());
		System.out.println("the name of person is:" + p.getName());
		System.out.println("the id of person is:" + p.getId());
		System.out.println("the phone number of person is:" + p.getPhone());
	}

	public static void deleteById() {
		System.out.println("enter the id of person you want to deleted");
		boolean b = pDao.delete(sc.nextInt());
		if (b)
			System.out.println("person details deleted");
		else
			System.out.println("unable to delete person details");
	}

	public static void saveAadhar() {
		System.out.println("enter the id of the person to add:");
		int id = sc.nextInt();
		System.out.println("enter the details of aadhar such as gender,uid,dob:");
		Aadhar a = new Aadhar();
		a.setGender(sc.next());
		a.setUid(sc.nextLong());
		a.setDob(LocalDate.parse(sc.next()));
		a = aDao.save(a, id);
		System.out.println("Aadhar added with id:" + a.getId());
	}

	public static void updateAadhar() {
		System.out.println("enter the details of aadhar such as id,gender,uid,dob:");
		Aadhar a = new Aadhar();
		a.setId(sc.nextInt());
		a.setGender(sc.next());
		a.setUid(sc.nextLong());
		a.setDob(LocalDate.parse(sc.next()));
		a = aDao.update(a);
		System.out.println("Aadhar updated with id:" + a.getId());

	}

	public static void findAadharById() {
		System.out.println("enter the id of aadhar you want to fetch");
		Aadhar a = aDao.findById(sc.nextInt());
		if(a!=null) {
		System.out.println("the gender of person is:" + a.getGender());
		System.out.println("the uid is:" + a.getUid());
		System.out.println("date of birth mentioned is:" + a.getDob());
		}
		System.err.println("no aadhar found with that id");
	}

	public static void deleteAadharById() {
		System.out.println("enter the id of person you want to deleted");
		boolean b = aDao.delete(sc.nextInt());
		if (b)
			System.out.println("aadhar details deleted");
		else
			System.err.println("unable to delete aadhar details");
	}

}
