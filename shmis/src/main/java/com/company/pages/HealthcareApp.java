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
        this.setSize(960, 540);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Login login = new Login();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.weightx = 0.5;
        c.weighty = 1;
        this.add(login, c);


        JPanel dummy = new JPanel(new GridBagLayout());
        dummy.setBackground(Color.cyan);
        c.gridx = 1;
        c.weightx = 0.5;
        this.add(dummy, c);

        this.setLocationRelativeTo(null);  // Center of the screen when opened
        // this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
