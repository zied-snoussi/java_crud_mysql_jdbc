package org.example;

import service.PersonService;
import entity.Person;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an instance of PersonService
        PersonService psr = new PersonService();

        // Create a new Person object
        Person p = new Person("Salah", "Ben Ahmed", 45);

        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);

        // Menu loop
        while (true) {
            System.out.println("1. Insert a new person");
            System.out.println("2. Update a person");
            System.out.println("3. Display all persons");
            System.out.println("4. Display a person by id");
            System.out.println("5. Delete a person");
            System.out.println("0. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        // Insert the new Person into the database
                        psr.insert(p);
                        System.out.println("Inserted a new person: " + p);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        // Update the Person in the database
                        p.setNom("Mohamed");
                        p.setPrenom("Ben Salah");
                        p.setAge(50);
                        psr.update(p);
                        System.out.println("Updated the person: " + p);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        // Read all Persons from the database
                        System.out.println("All persons in the database:");
                        for (Person person : psr.readAll()) {
                            System.out.println(person);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        // Read the Person by id from the database
                        System.out.print("Enter the id of the person: ");
                        int id = scanner.nextInt();
                        Person readPerson = psr.readById(id);
                        System.out.println("Read person by id: " + readPerson);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        // Delete the Person from the database
                        System.out.print("Enter the id of the person to delete: ");
                        int id = scanner.nextInt();
                        p.setId(id);
                        psr.delete(p);
                        System.out.println("Deleted the person with id: " + id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 5.");
            }
        }
    }
}