package com.company.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.HashMap;

import com.company.classes.Appointment;
import com.company.classes.Patient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadWrite {
    public static FileReader fileRead;
    public static FileWriter fileWrite;
    public static BufferedReader bufferRead;
    public static BufferedWriter bufferWrite;
    public static JSONParser parser;

    public static HashMap<String, Patient> readPatients() {
        HashMap<String, Patient> mp = new HashMap<String, Patient>();
        JSONObject obj = fileToJSONObject("patients.json");

        for (Object entry : obj.entrySet()) {
            String key = ((HashMap.Entry<String, JSONObject>)entry).getKey();
            JSONObject value = ((HashMap.Entry<String, JSONObject>)entry).getValue();
            mp.put(key, Patient.fromJSONObject(value));
        }

        return mp;
    }

    // not pure JSON hashmap since I may need to create patient objects
    public static HashMap<String, Appointment> readAppointments() {
        HashMap<String, Appointment> mp = new HashMap<String, Appointment>();
        JSONObject obj = fileToJSONObject("appointments.json");

        for (Object entry : obj.entrySet()) {
            String key = ((HashMap.Entry<String, JSONObject>)entry).getKey();
            JSONObject value = ((HashMap.Entry<String, JSONObject>)entry).getValue();
            mp.put(key, Appointment.fromJSONObject(value));
        }

        return mp;
    }

    public static JSONObject fileToJSONObject(String fileName) {
        JSONObject obj = new JSONObject();

        try {
            fileRead = new FileReader(fileName, Charset.forName("UTF8"));
            bufferRead = new BufferedReader(fileRead);
            parser = new JSONParser();

            String input = bufferRead.readLine();
            obj = (JSONObject) parser.parse(input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    // Writes whole patient or appointment json file
    public static void writeFile(JSONObject obj, String fileName) {
        try {
            fileWrite = new FileWriter(fileName, Charset.forName("UTF8"));
            bufferWrite = new BufferedWriter(fileWrite);

            bufferWrite.write(obj.toJSONString());
            bufferWrite.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
