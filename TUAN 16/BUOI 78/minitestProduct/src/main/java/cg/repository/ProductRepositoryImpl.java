package cg.repository;

import cg.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class ProductRepositoryImpl implements IProductRepository{

    private static SessionFactory sessionFactory;
    private static  EntityManager entityManager;


    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Product> selectAll() {
        String SELECT_ALL = "SELECT p FROM Product AS p";
        TypedQuery<Product> query = entityManager.createQuery(SELECT_ALL, Product.class);
        return query.getResultList();
    }

    @Override
    public Product create(Product product) {
        Transaction transaction = null;
        Product origin;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (product.getId() != 0) {
                origin = selectById(product.getId());
                origin.setName(product.getName());
                origin.setPrice(product.getPrice());
                origin.setDescription(product.getDescription());
            } else {
                origin = product;
            }
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Product delete(int id) {
        Transaction transaction = null;
        Product origin = selectById(id);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (origin != null) {
                session.delete(origin);
            }
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public Product selectById(int id) {
        String SELECT_BY_ID = "SELECT p FROM Product AS p WHERE p.id = :id";
        TypedQuery<Product> query = entityManager.createQuery(SELECT_BY_ID, Product.class);
        query.setParameter("id", id);
        return  query.getSingleResult();
    }

    @Override
    public List<Product> search(String key) {
        String SEARCH_ALL = "SELECT p FROM Product AS p WHERE p.name = :key";
        TypedQuery<Product> query = entityManager.createQuery(SEARCH_ALL, Product.class);
        query.setParameter("key", key);
        return query.getResultList();
    }


}
