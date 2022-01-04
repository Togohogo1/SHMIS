package com.company.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class HealthcareApp extends JFrame {
    public HealthcareApp() {
        super("Simple Healthcare Management Interface for Specialists");
        this.setSize(1200, 675);

        this.add(new Login());

        this.setLocationRelativeTo(null);  // Center of the screen when opened
        // this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
