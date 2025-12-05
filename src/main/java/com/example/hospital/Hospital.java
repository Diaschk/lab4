package com.example.hospital;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public Patient addPatient(String name, int age) {
        Patient patient = new Patient(name, age);
        patients.add(patient);
        return patient;
    }

    public Doctor addDoctor(String name, String specialty) {
        Doctor doctor = new Doctor(name, specialty);
        doctors.add(doctor);
        return doctor;
    }

    public Appointment bookAppointment(Patient patient, Doctor doctor, String date) {
        if (!patients.contains(patient)) throw new IllegalArgumentException("Patient not registered");
        if (!doctors.contains(doctor)) throw new IllegalArgumentException("Doctor not registered");
        Appointment appointment = new Appointment(patient, doctor, date);
        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    public int getPatientCount() {
        return patients.size();
    }

    public Patient getPatientByName(String name) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public Doctor getDoctorByName(String name) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) return d;
        }
        return null;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
