package com.company.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.company.pages.Login;


public class HealthcareApp extends JFrame {
    public HealthcareApp() {
        super("Simple Healthcare Management Interface for Specialists");

        this.setSize(1200, 675);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Login login = new Login();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.weightx = 0.25;
        c.weighty = 1;
        this.add(login.sidebar(), c);

        JPanel dummy = new JPanel(new GridBagLayout());
        dummy.setBackground(Color.cyan);
        c.gridx = 1;
        c.weightx = 0.75;
        this.add(login.login(), c);

        this.setLocationRelativeTo(null);  // Center of the screen when opened
        // this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
