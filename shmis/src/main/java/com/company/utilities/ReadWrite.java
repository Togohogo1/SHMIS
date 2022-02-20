package com.company.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadWrite {
    private static FileReader fileRead;
    private static FileWriter fileWrite;
    private static BufferedReader bufferRead;
    private static BufferedWriter bufferWrite;
    private static JSONParser parser;

    public static JSONObject readFile(String fileName) {
        JSONObject obj = new JSONObject();

        try {
            fileRead = new FileReader(fileName, Charset.forName("UTF8"));
            bufferRead = new BufferedReader(fileRead);
            parser = new JSONParser();

            String input = bufferRead.readLine();
            obj = (JSONObject) parser.parse(input);
        } catch (Exception e) {
            System.out.println(e);
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
            System.out.println(e);
        }
    }
}
