package project.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import project.entity.Destination;
import project.util.HibernateUtil;

import java.util.List;

public class DestinationDAO {

    public void save(Destination destination) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(destination);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Destination> getAllDestinations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Destination", Destination.class).list();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
