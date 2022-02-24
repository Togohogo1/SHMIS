package com.company;

import java.io.*;
import java.util.*;

import com.company.classes.*;
import com.company.pages.HealthcareApp;
import com.company.utilities.*;
import com.company.utilities.Queue;

import org.json.simple.*;

public class BackendTest {
    public static void main(String[] args) {
        test();
    }

    public static String test9(long start) {
        long min = start*30 + 540;
        return String.format("%02d:%02d", min/60, min%60);
    }

    public static int stringToStart(String time) {
        String[] arr = time.split(":");
        int min = Integer.parseInt(arr[0])*60 + Integer.parseInt(arr[1]);
        int numMin = (min-540) / 30;
        return numMin;
    }

    public static void test() {
        Queue q = new Queue();
        q.insertFront(new Node(2));
        q.insertFront(new Node(9));//
        q.insertFront(new Node(12));//
        q.insertFront(new Node(3));
        q.insertFront(new Node(7));
        q.insertFront(new Node(91));//
        q.insertFront(new Node(31));//
        q.remove(91);
        q.remove(31);
        q.remove(12);
        q.remove(9);
    }

}
