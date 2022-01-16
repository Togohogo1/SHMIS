package com.company;

import com.company.pages.HealthcareApp;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
    public static void main(String[] args) {
        FlatDarkLaf.install();
        FlatLightLaf.install();
        HealthcareApp shmis = new HealthcareApp();
    }
}
