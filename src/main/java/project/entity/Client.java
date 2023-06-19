package project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode


public class Client extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "budget")
    private double budget;

    @OneToMany(mappedBy = "client")
    private List<Orders> order;

    public Client(String name, String surname, double budget) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.order = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    public Client addClient(Client client){
        return new Client();
    }
}