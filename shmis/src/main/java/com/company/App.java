package com.company;

import javax.swing.UIManager;

import com.company.pages.HealthcareApp;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
    public static void main(String[] args) {
        FlatLightLaf.install();
        UIManager.put("Button.arc", 0);
        HealthcareApp shmis = new HealthcareApp();
    }
}
