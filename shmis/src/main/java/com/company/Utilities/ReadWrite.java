package com.company.Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import com.company.Appointment;
import com.company.Patient;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadWrite {
    public static FileReader fileRead;
    public static FileWriter fileWrite;
    public static BufferedReader bufferRead;
    public static BufferedWriter bufferWrite;
    public static JSONParser parser;

    public static ArrayList<Patient> readPatients() {
        return null;
    }

    // note pure JSON hashmap since I may need to create patient objects
    public static HashMap<String, Appointment> readAppointments() {
        HashMap<String, Appointment> mp = new HashMap<String, Appointment>();

        try {
            fileRead = new FileReader("appointments.json", Charset.forName("UTF8"));
            bufferRead = new BufferedReader(fileRead);
            parser = new JSONParser();

            String input = bufferRead.readLine();
            JSONObject obj = (JSONObject) parser.parse(input);

            for (Object elem : obj.entrySet()) {
                HashMap.Entry<String, JSONObject> entry = (HashMap.Entry<String, JSONObject>) elem;
                String key = entry.getKey();
                JSONObject value = entry.getValue();
                System.out.println(value.get("xRay").getClass());
                mp.put(key, Appointment.fromJSONObject(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e); // TODO create file if it doesn't exist
        }

        return mp;
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
