package com.unitbv.billing.dal;

import com.unitbv.billing.dao.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Product addProduct(Product product) {
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
    public List<Product> getProductsDetails(List<Product> products) {
        ArrayList<Product> productsDb = new ArrayList<>();
        try {
            em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> rootEntry = cq.from(Product.class);
            CriteriaQuery<Product> all = cq.select(rootEntry);
            TypedQuery<Product> allQuery = em.createQuery(all);
            productsDb.addAll(allQuery.getResultList());
            for (Product product : productsDb) {
                if (products.contains(product)) {
                    products.get(products.indexOf(product)).setPrice(product.getPrice() - (product.getDiscount() / 100.0) * product.getPrice());
                }
            }
            return products;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}
