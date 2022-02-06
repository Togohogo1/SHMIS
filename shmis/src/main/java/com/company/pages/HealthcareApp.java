package com.company.pages;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class HealthcareApp extends JFrame implements WindowListener {
    private JPanel login = new Login();
    private JPanel program = new Program();

    public HealthcareApp() {
        super("Simple Healthcare Management Interface for Specialists");

        this.addWindowListener(this);
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

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing");
        while (true) {
            System.out.println("never closing >:)");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
