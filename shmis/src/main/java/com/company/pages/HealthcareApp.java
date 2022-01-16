package com.company.pages;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class HealthcareApp extends JFrame {
    public HealthcareApp() {
        super("Simple Healthcare Management Interface for Specialists");

        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);  // Center of the screen when opened
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
