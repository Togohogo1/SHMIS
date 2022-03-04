package com.company.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Class for managing all file reading and writing.
 */
public class ReadWrite {
    private static FileReader fileRead;
    private static FileWriter fileWrite;
    private static BufferedReader bufferRead;
    private static BufferedWriter bufferWrite;
    private static JSONParser parser;

    /**
     * Returns the JSONObject read from <code>fileName.json</code>.
     *
     * @param fileName The file name
     * @return The JSONObject read from the file
     */
    public static JSONObject readFile(String fileName) {
        JSONObject obj = new JSONObject();

        try {
            // Requires a later version of Java
            fileRead = new FileReader(fileName, Charset.forName("UTF8"));  // Allows Unicode characters
            bufferRead = new BufferedReader(fileRead);
            parser = new JSONParser();

            // Converting to a JSONObject
            String input = bufferRead.readLine();
            obj = (JSONObject) parser.parse(input);

        } catch (Exception e) {
            // Will throw an error when program first initializes, but files will still be created
            System.out.println(e);
        }

        return obj;
    }

    /**
     * Writes a JSONObject to <code>fileName.java</code>.
     *
     * @param obj The JSONObject to be written
     * @param fileName The file name
     */
    public static void writeFile(JSONObject obj, String fileName) {
        try {
            fileWrite = new FileWriter(fileName, Charset.forName("UTF8"));
            bufferWrite = new BufferedWriter(fileWrite);

            // Writing to file
            bufferWrite.write(obj.toJSONString());
            bufferWrite.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
