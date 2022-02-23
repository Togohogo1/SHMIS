package com.company;

import com.company.pages.HealthcareApp;
import com.company.utilities.DSManager;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
    public static HealthcareApp shmis;
    public static DSManager dsm;

    public static void main(String[] args) {
        FlatLightLaf.install();
        dsm = new DSManager();
        shmis = new HealthcareApp();
    }
}
