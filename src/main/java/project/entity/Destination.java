package project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destination")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode



public class Destination extends BaseEntity{

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "hotel")
    private String hotel;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "destination")
    private List<Orders> order;

    public Destination(String country, String city, String hotel, double price) {
        this.country = country;
        this.city = city;
        this.hotel = hotel;
        this.price = price;
        this.order = new ArrayList<>();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

}