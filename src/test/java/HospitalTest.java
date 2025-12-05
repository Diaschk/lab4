import com.example.hospital.Appointment;
import com.example.hospital.Doctor;
import com.example.hospital.Hospital;
import com.example.hospital.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class HospitalTest {

    private Hospital hospital;
    private Doctor doctor;
    private Patient patient;


    @BeforeEach
    void setUp() {
        hospital = new Hospital();
        patient = hospital.addPatient("Yana Vlasyk", 20);
        doctor = hospital.addDoctor("Dr. Stepanenko", "Surgeon");
    }

    @Test
    void testAddPatient() {
        assertTrue(hospital.getAppointments().isEmpty());
        assertEquals("Yana Vlasyk", patient.getName());
        assertEquals(20, patient.getAge());
    }

    @Test
    void testAddDoctor() {
        assertEquals("Dr. Stepanenko", doctor.getName());
        assertEquals("Surgeon", doctor.getSpecialty());
    }

    @Test
    void testBookAppointment() {
        Appointment appointment = hospital.bookAppointment(patient, doctor, "2025-09-27");
        assertEquals(patient, appointment.getPatient());
        assertEquals(doctor, appointment.getDoctor());
        assertEquals("2025-09-27", appointment.getDate());
        assertTrue(hospital.getAppointments().contains(appointment));
    }

    @Test
    void testBookUnregisteredPatient() {
        Patient newPatient = new Patient("Diana", 15);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                hospital.bookAppointment(newPatient, doctor, "2025-09-29"));
        assertEquals("Patient not registered", exception.getMessage());
    }

    @Test
    void testBookUnregisteredDoctor() {
        Doctor newDoctor = new Doctor("Dr. Vasylenko", "Pediatrician");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                hospital.bookAppointment(patient, newDoctor, "2025-09-28"));
        assertEquals("Doctor not registered", exception.getMessage());
    }

    @Test
    void testPatientCount() {
        Hospital hospital = new Hospital();

        assertEquals(0, hospital.getPatientCount());

        hospital.addPatient("Diana", 20);
        hospital.addPatient("Yaroslav", 21);

        assertEquals(2, hospital.getPatientCount());
    }
}
