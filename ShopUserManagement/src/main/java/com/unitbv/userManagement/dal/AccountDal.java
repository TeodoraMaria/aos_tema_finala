package com.unitbv.userManagement.dal;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.unitbv.userManagement.dao.Person;

public class AccountDal implements IAccount {

	private EntityManager em;
	private EntityManagerFactory emf;

	public AccountDal(String connectionString) {
		try {
			emf = Persistence.createEntityManagerFactory(connectionString);
		} catch (Exception ex) {
			emf = null;
			ex.printStackTrace();
		}
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void close() {
		if (emf != null) {
			emf.close();
		}
	}

	@Override
	public Person login(Person person){
		try {
			Person foundPerson = findPerson(person);
			em = emf.createEntityManager();
			em.getTransaction().begin();
			if(foundPerson!=null){
				return foundPerson;
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return  null;
	}

	@Override
	public boolean insert(Person person) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			person = em.merge(person);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			em.close();
		}
		return  true;
	}

	public ArrayList<Person> getAll() {
		ArrayList<Person> people = new ArrayList<Person>();
		try {
			em = emf.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Person> cq = cb.createQuery(Person.class);
			Root<Person> rootEntry = cq.from(Person.class);
			CriteriaQuery<Person> all = cq.select(rootEntry);
			TypedQuery<Person> allQuery = em.createQuery(all);
			people.addAll(allQuery.getResultList());
			return people;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			em.close();
		}
	}


	private Person findPerson(Person person) {
		ArrayList<Person> all = getAll();
		for (Person p : all) {
			if (p.getEmail().equals(person.getEmail()) && p.getPassword().equals(person.getPassword())) {
				return p;
			}
		}
		return null;
	}
}
