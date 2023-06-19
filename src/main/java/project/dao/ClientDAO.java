package project.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import project.entity.Client;
import project.entity.Destination;
import project.util.HibernateUtil;

import java.util.List;

public class ClientDAO {

    public void save(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Client", Client.class).list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }





}
