package com.company.pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class HealthcareApp extends JFrame {
    private JPanel login = new Login();
    private JPanel program = new Program();

    public HealthcareApp() {
        super("Simple Healthcare Management Interface for Specialists");

        this.add(login);  // Default (changed to program for testing purposes)
        this.setSize(1024, 576);
        this.setLocationRelativeTo(null);  // Center of the screen when opened
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void LoggedIn() {  // Precondition: login page is in the JFrame
        this.remove(login);
        this.add(program);
    }

    public void LogOut() {  // Precondition: program page is in the JFrame
        this.remove(program);
        this.add(login);
        // Remember to reset some values (like current user)
    }
}
