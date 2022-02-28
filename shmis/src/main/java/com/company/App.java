package com.company;

import com.company.pages.HealthcareApp;
import com.company.utilities.DSManager;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * Class to run the program.
 */
public class App {
    public static HealthcareApp shmis;
    public static DSManager dsm;

    // TODO fix color bug
    // TODO login as a separate panel

    /**
     * The main method initializes a theme, the data structure manager and creates
     * the program.
     */
    public static void main(String[] args) {
        FlatLightLaf.install();
        dsm = new DSManager();
        shmis = new HealthcareApp();
    }
}
