package com.company;

import java.awt.Color;

import javax.swing.Painter;
import javax.swing.UIManager;

import com.company.pages.HealthcareApp;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
    public static HealthcareApp shmis;
    public static void main(String[] args) {
        FlatDarkLaf.install();
        FlatLightLaf.install();
        shmis = new HealthcareApp();
    }
}
