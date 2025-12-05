package com.example.hospital;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital();

        System.out.println("Welcome to Clinic Service!");
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. List Appointments");
            System.out.println("5. List Patients");
            System.out.println("6. List Doctors");
            System.out.println("7. Exit");
            System.out.print("Your choice: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Please enter a number!");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Patient name: ");
                    String name = sc.nextLine();
                    System.out.print("Patient age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    Patient p = hospital.addPatient(name, age);
                    System.out.println("Patient added: " + p.getName());
                }
                case 2 -> {
                    System.out.print("Doctor name: ");
                    String name = sc.nextLine();
                    System.out.print("Doctor specialty: ");
                    String spec = sc.nextLine();
                    Doctor d = hospital.addDoctor(name, spec);
                    System.out.println("Doctor added: " + d.getName());
                }
                case 3 -> {
                    System.out.print("Patient name: ");
                    String pname = sc.nextLine();
                    System.out.print("Doctor name: ");
                    String dname = sc.nextLine();
                    System.out.print("Appointment date: ");
                    String date = sc.nextLine();

                    Patient patient = hospital.getPatientByName(pname);
                    Doctor doctor = hospital.getDoctorByName(dname);
                    if (patient != null && doctor != null) {
                        Appointment a = hospital.bookAppointment(patient, doctor, date);
                        System.out.println("Appointment booked on " + a.getDate());
                    } else {
                        System.out.println("Patient or Doctor not found!");
                    }
                }
                case 4 -> {
                    System.out.println("All appointments:");
                    for (Appointment a : hospital.getAppointments()) {
                        System.out.println(a.getDate() + " - " + a.getPatient().getName() + " with " + a.getDoctor().getName());
                    }
                }
                case 5 -> {
                    System.out.println("List of patients:");
                    for (Patient p : hospital.getPatients()) {
                        System.out.println("Name: " + p.getName() + ", Age: " + p.getAge());
                    }
                }
                case 6 -> {
                    System.out.println("List of doctors:");
                    for (Doctor d : hospital.getDoctors()) {
                        System.out.println("Name: " + d.getName() + ", Specialty: " + d.getSpecialty());
                    }
                }
                case 7 -> {
                    running = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
