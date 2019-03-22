package Test;

import java.time.LocalDateTime;
import java.util.List;

public class Registration {
    private static List<Registration> registrations;
    private User doctor;
    private User patient;
    private LocalDateTime timeOfRegistration;
    public static void addRegistration(Registration r){
        registrations.add(r);
    }
    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public LocalDateTime getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public boolean setTimeOfRegistration(LocalDateTime timeOfRegistration, User doc) {
        //тут где-то надо проверить на свободное время для доктора
        this.timeOfRegistration = timeOfRegistration;
        return true;
    }
}
