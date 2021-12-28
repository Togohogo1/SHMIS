package com.company;

import java.util.ArrayList;
import java.util.HashMap;

import com.company.classes.Appointment;
import com.company.classes.Patient;
import com.company.utilities.MergeSort;
import com.company.utilities.OnlineDataManager;
import com.company.utilities.ReadWrite;

import org.json.simple.JSONObject;

public class App {
    public static void main(String[] args) {
        JSONObject obj = ReadWrite.readFile("patients.json");
        OnlineDataManager odm = new OnlineDataManager(); // bruh defeats the purpose of static
        ArrayList<Patient> arr = OnlineDataManager.patientList;
        ArrayList<Patient> arr2 = OnlineDataManager.patientListSort;
        for (Patient i : arr2) {
            System.out.println(i.getFirstName());
        }
        MergeSort.mergeSort(arr2, "firstName");
        System.out.println();

        for (Patient i : arr2) {
            System.out.println(i.getFirstName());
        }

        MergeSort.mergeSort(arr2, "lastName");

        System.out.println();

        for (Patient i : arr2) {
            System.out.println(i.getLastName());
        }
        MergeSort.mergeSort(arr2, "email");
        for (Patient i : arr2) {
            System.out.println(i.getEmail());
        }
        System.out.println("done");
    }
}
