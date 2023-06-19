package project.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import project.entity.Client;
import project.entity.Destination;
import project.entity.Orders;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Destination.class)
                    .addAnnotatedClass(Orders.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
