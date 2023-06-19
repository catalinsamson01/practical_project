package project.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import project.util.HibernateUtil;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "destination_id")
    private Destination destination;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void reserve(Client client, Destination destination) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            double budgetClient = client.getBudget();
            double priceDestination = destination.getPrice();
            Orders order = new Orders();

            if (priceDestination <= budgetClient) {
                order.setClient(client);  // Setează clientul pentru comandă
                order.setDestination(destination);  // Setează destinația pentru comandă
                session.saveOrUpdate(client);
                session.saveOrUpdate(destination);
                session.saveOrUpdate(order);
                session.refresh(client);
                session.refresh(destination);
                if (transaction.isActive()) {
                    transaction.commit();
                }
                System.out.println(client.getName() + " " + client.getSurname() + "'s reservation for " + destination.getCity()
                        + " " + destination.getCountry() + " has been done successfully!");
            } else {
                System.out.println(client.getName() + " " + client.getSurname() + " doesn't have the amount for  " + destination.getCity()
                        + " " + destination.getCountry());
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void cancelReservation(Client client, Destination destination) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Orders> query = criteriaBuilder.createQuery(Orders.class);
            Root<Orders> root = query.from(Orders.class);
            Predicate condition = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("client"), client),
                    criteriaBuilder.equal(root.get("destination"), destination));

            query.select(root).where(condition);

            List<Orders> orders = session.createQuery(query).getResultList();
            if (!orders.isEmpty()) {
                Orders order = orders.get(0);
                session.delete(order);
                transaction.commit();
                System.out.println(client.getName() + " " + client.getSurname() + " successfully canceled the reservation for "
                        + order.getDestination().getCity() + " "
                        + order.getDestination().getCountry());
            } else {
                System.out.println("No matching reservation found for " + client.getName() + " " + client.getSurname());
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


}