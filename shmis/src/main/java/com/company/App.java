package com.company;

import java.util.ArrayList;
import java.util.HashMap;

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
        test1();
    }

    public static void test1() {
        JSONObject obj = ReadWrite.readFile("patients.json");
        DataStructureManager odm = new DataStructureManager();
        ArrayList<Patient> arr = odm.patientList;
        ArrayList<Patient> arr2 = odm.patientListSort;
        ReadWrite.writeFile(obj, "patients.json");
    }

    public static void test2() {
        DoublyLinkedList dll = new DoublyLinkedList();
        System.out.println(dll.head == dll.tail);
        System.out.println(dll.head.equals(dll.tail));
        System.out.println("end");
    }
}
