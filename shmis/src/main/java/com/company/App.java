package com.company;

import java.util.HashMap;

import com.company.Classes.Appointment;
import com.company.Utilities.ReadWrite;

public class App {
    public static void main(String[] args) {
        boolean[] xray = {true, true, true, true, true, true, true};
        boolean[] ult = {true, true, true, true};

        Appointment app = new Appointment(
            600,
            660,
            "December 27, 2021",
            "Approved",
            "app_1",
            "John Smith",
            "Brian",
            "notes notes notes",
            xray,
            ult
        );

        // System.out.println(req.toJSONObject());
        // System.out.println(app.toJSONObject());

        // Patient p1 = new Patient(34, "John", "Smith", "Male", "johnsmith_1", "password", "123 Sesame Street", "john.smith@fakemail.com", "January 1, 1987", "647-123-4567");
        // Patient p2 = new Guest(34, "John", "Smith", "Male", "johnsmith_1", "password", "123 Sesame Street", "john.smith@fakemail.com", "January 1, 1987", "647-123-4567");

        // p1.addAppointment(app);
        // System.out.println(p1.toJSONObject());
        // System.out.println(p2.toJSONObject());
        HashMap<String, Appointment> a = ReadWrite.readAppointments();

        System.out.println(a.get("app_1").getID());
        System.out.println(a.get("app_2").getID());
        System.out.println(a.get("app_3").getID());
        System.out.println(a.get("app_4").getID());

        // ReadWrite.writeFile(app.toJSONObject(), "appointments.json");
    }
}
