package com.unitbv.warehouse.dal;

import com.unitbv.warehouse.dao.Product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ProductDal implements IProduct {

	private EntityManager em;
	private EntityManagerFactory emf;

	public ProductDal(String connectionString) {
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

	private Product findProduct(Product person) {
		List<Product> all = getAllProducts();
		for (Product p : all) {
			if (p.getName().equals(person.getName())) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Product addNewProduct(Product product) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			product = em.merge(product);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		try {
			em = emf.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Product> cq = cb.createQuery(Product.class);
			Root<Product> rootEntry = cq.from(Product.class);
			CriteriaQuery<Product> all = cq.select(rootEntry);
			TypedQuery<Product> allQuery = em.createQuery(all);
			products.addAll(allQuery.getResultList());
			return products;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			em.close();
		}
	}
}
