package project.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import project.entity.Orders;
import project.util.HibernateUtil;

import java.util.List;

public class OrderDAO {

    public void save(Orders order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Orders> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Order", Orders.class).list();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}

