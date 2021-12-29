package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import com.company.classes.Appointment;
import com.company.classes.Patient;
import com.company.utilities.MergeSort;
import com.company.utilities.Node;
import com.company.utilities.DataStructureManager;
import com.company.utilities.DoublyLinkedList;
import com.company.utilities.ReadWrite;

import org.json.simple.JSONObject;

public class App {
    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        JSONObject obj = ReadWrite.readFile("patients.json");
        DataStructureManager odm = new DataStructureManager();
        ArrayList<Patient> arr = odm.patientList;

        ReadWrite.writeFile(obj, "patients.json");
    }

    public static void test2() {
        DoublyLinkedList dll = new DoublyLinkedList();
        System.out.println(dll.head == dll.tail);
        System.out.println(dll.head.equals(dll.tail));
        System.out.println("end");
    }

    public static void test3() {
        ArrayList<Boolean> xr = new ArrayList<>(Arrays.asList(true,false,false,false,true,true,true));
        ArrayList<Boolean> us = new ArrayList<>(Arrays.asList(false,false,false,false));
        Appointment app1 = new Appointment(0, 0, 1, "date", "status", "patient", "referralDoctor", "notes", xr, us);
        Appointment app2 = new Appointment(0, 0, 2, "date", "status", "patient", "referralDoctor", "notes", xr, us);
        Appointment app3 = new Appointment(0, 0, 3, "date", "status", "patient", "referralDoctor", "notes", xr, us);
        Appointment app4 = new Appointment(0, 0, 4, "date", "status", "patient", "referralDoctor", "notes", xr, us);
        Appointment app5 = new Appointment(0, 0, 5, "date", "status", "patient", "referralDoctor", "notes", xr, us);
        Appointment app6 = new Appointment(0, 0, 6, "date", "status", "patient", "referralDoctor", "notes", xr, us);
        DataStructureManager dsm = new DataStructureManager();
        dsm.packPatients();
        JSONObject obj = ReadWrite.readFile("patients.json");
        dsm.appointmentList.add(app1);
        dsm.appointmentList.add(app2);
        dsm.appointmentList.add(app3);
        dsm.appointmentList.add(app4);
        dsm.appointmentList.add(app5);
        dsm.appointmentList.add(app6);
        System.out.println(dsm.packAppointments());
        System.out.println(dsm.patientList);
        ReadWrite.writeFile(dsm.packAppointments(), "appointments.json");
    }
}
