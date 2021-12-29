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

public class App {  // TODO try to cast jsonarray to jsonlist? => use arraylist for requisition, numbers for id, move JSONToPatient to dsmanager
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
        dll.insertFront(new Node(new Appointment(1, 0, null, null, null, null, null, null, null, null)));
        dll.removeFront();
        dll.insertBack(new Node(new Appointment(2, 0, null, null, null, null, null, null, null, null)));
        dll.insertFront(new Node(new Appointment(3, 0, null, null, null, null, null, null, null, null)));
        dll.removeFront();
        dll.insertBack(new Node(new Appointment(4, 0, null, null, null, null, null, null, null, null)));
        dll.insertBack(new Node(new Appointment(5, 0, null, null, null, null, null, null, null, null)));
        dll.insertBack(new Node(new Appointment(6, 0, null, null, null, null, null, null, null, null)));
        dll.insertBack(new Node(new Appointment(7, 0, null, null, null, null, null, null, null, null)));
        dll.insertFront(new Node(new Appointment(8, 0, null, null, null, null, null, null, null, null)));
        dll.insertBack(new Node(new Appointment(9, 0, null, null, null, null, null, null, null, null)));
        dll.removeFront();
        dll.removeBack();
        dll.insertBack(new Node(new Appointment(10, 0, null, null, null, null, null, null, null, null)));
        dll.insertBack(new Node(new Appointment(11, 0, null, null, null, null, null, null, null, null)));
        dll.insertBack(new Node(new Appointment(12, 0, null, null, null, null, null, null, null, null)));
        dll.insertFront(new Node(new Appointment(13, 0, null, null, null, null, null, null, null, null)));
        dll.insertBack(new Node(new Appointment(14, 0, null, null, null, null, null, null, null, null)));
        dll.removeBack();
        dll.removeBack();
        dll.traverse();
        System.out.println("end");
    }
}
