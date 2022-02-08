package com.company;

import java.awt.Color;

import javax.swing.Painter;
import javax.swing.UIManager;

import com.company.pages.HealthcareApp;
import com.company.utilities.DSManager;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
    public static HealthcareApp shmis;
    public static DSManager dsm;
    public static void main(String[] args) {
        FlatDarkLaf.install();
        FlatLightLaf.install();
        dsm = new DSManager();
        shmis = new HealthcareApp();
    }
}
