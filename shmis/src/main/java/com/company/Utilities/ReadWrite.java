package com.company.Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.company.Appointment;
import com.company.Patient;

import org.json.simple.JSONObject;

public class ReadWrite {
    public static FileWriter file;
    public static BufferedWriter buffer;


    public static ArrayList<Patient> readPatients() {
        return null;
    }

    public static ArrayList<Appointment> readAppointments() {
        return null;
    }

    // Writes whole patient or appointment json file
    public static void writeFile(JSONObject obj, String fileName) {
        try {
            file = new FileWriter(fileName, Charset.forName("UTF8"));
            buffer = new BufferedWriter(file);
            buffer.write(obj.toJSONString());
            buffer.newLine();
            buffer.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
