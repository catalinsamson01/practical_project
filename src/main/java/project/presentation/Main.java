package project.presentation;

import project.entity.Client;
import project.entity.Destination;
import project.entity.Orders;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Client client1 = new Client("Ion", "Ionescu", 1000.5);
        Client client2 = new Client("Constanin", "Constantinescu", 5000);
        Client client3 = new Client("Alex", "Popescu", 50);
        Client client4 = new Client("Ionut", "Podaru", 5000);
        Client client5 = new Client("Vasile", "Vasilescu", 5000.5);
        Client client6 = new Client("Sergiu", "Sergiulescu", 500.53);

        Destination destination = new Destination();
        Destination destination1 = new Destination("Romania", "Brasov", "hotel 5 stele", 250.99);
        Destination destination2 = new Destination("Thailand", "Bangkok", "hotel 5 stele", 1000);
        Destination destination3 = new Destination("France", "Paris", "hotel 5 stele", 1000);

        Orders order = new Orders();
        order.reserve(client1, destination1);
        order.reserve(client2, destination1);
        order.reserve(client3, destination2);
        order.reserve(client4, destination2);
        order.reserve(client5, destination3);
        order.reserve(client6, destination3);

        order.cancelReservation(client2, destination1);









    }
}
