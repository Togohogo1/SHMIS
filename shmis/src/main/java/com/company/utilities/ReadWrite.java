package com.company.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadWrite {
    public static FileReader fileRead;
    public static FileWriter fileWrite;
    public static BufferedReader bufferRead;
    public static BufferedWriter bufferWrite;
    public static JSONParser parser;

    public static JSONObject readFile(String fileName) {
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
