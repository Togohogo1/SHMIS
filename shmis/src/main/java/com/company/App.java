package com.company;

import javax.swing.UIManager;

import com.company.pages.HealthcareApp;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
    public static void main(String[] args) {
        FlatLightLaf.install();
        HealthcareApp shmis = new HealthcareApp();
    }
}
