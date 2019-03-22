package Test;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PatientRegistrator implements Command {
    Patient patient;
    Doctor doctor;
    public static final String NAME = "Записать пациента к врачу";
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void run(Scanner sc) {
        patient = new Patient();//Знаю что создаю лишний объект но времени мало,
        doctor = new Doctor();//не придумал как по другому пока.
        System.out.println("Введите имя пациента");
        System.out.println(getLoginList(patient));
        String patientLogin = sc.next();
        User patientToReg = patient.getRoleMembers().get(patientLogin);//Я не проверяю на валидность, иначе вообще ничего не успею
        System.out.println("Введите имя доктора");
        System.out.println(getLoginList(doctor));
        String doctorLogin = sc.next();
        User doctorToReg = doctor.getRoleMembers().get(doctorLogin);

        Registration registration = new Registration();
        registration.setPatient(patientToReg);
        registration.setDoctor(doctorToReg);
        boolean freetime = false;
        System.out.println("Введите время");
        while(!freetime){

            String time = sc.next();
            LocalDateTime ldt;
            ldt = LocalDateTime.parse(time);
            if(registration.setTimeOfRegistration(ldt, doctorToReg)) break;//там должна быть проверка на свободное время
            System.out.println("Время занято или вне рабочего времени врачей, введите новое");
        }
        Registration.addRegistration(registration);



    }

    private String getLoginList(Role role){
        StringBuilder sb = new StringBuilder();

        for(String login: role.getRoleMembers().keySet()){
            sb.append("[" +login + "]").append(System.lineSeparator());
        }
        return sb.toString();

    }


}
