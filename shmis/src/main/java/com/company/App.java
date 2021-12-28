package com.company;

import java.util.ArrayList;
import java.util.HashMap;

import com.company.classes.Appointment;
import com.company.classes.Patient;
import com.company.utilities.MergeSort;
import com.company.utilities.OnlineDataManager;
import com.company.utilities.ReadWrite;

import org.json.simple.JSONObject;

public class App {  // TODO handle empty file
    public static void main(String[] args) {
        JSONObject obj = ReadWrite.readFile("patients.json");
        OnlineDataManager odm = new OnlineDataManager();
        ArrayList<Patient> arr = odm.patientList;
        ArrayList<Patient> arr2 = odm.patientListSort;
    }
}
