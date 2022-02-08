package com.company.pages;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.company.App;
import com.company.classes.Appointment;
import com.company.utilities.DSManager;
import com.company.utilities.ReadWrite;

public class HealthcareApp extends JFrame implements WindowListener {
    private JPanel login;
    private JPanel program;

    public HealthcareApp() {
        super("Simple Healthcare Management Interface for Specialists");

        login = new Login();
        this.add(login);  // Default (changed to program for testing purposes)

        this.addWindowListener(this);
        this.setSize(1024, 576);
        this.setLocationRelativeTo(null);  // Center of the screen when opened
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void LoggedIn() {  // Precondition: login page is in the JFrame
        program = new Program();  // Avoid NullPointerException
        this.remove(login);
        this.add(program);
        this.revalidate();
        this.repaint();
    }

    public void LogOut() {  // Precondition: program page is in the JFrame
        this.remove(program);
        this.add(login);
        this.revalidate();
        this.repaint();
        // Remember to reset some values (like current user)
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        ReadWrite.writeFile(App.dsm.packPatients(), "patients.json");
        ReadWrite.writeFile(App.dsm.packAppointments(), "appointments.json");
        ReadWrite.writeFile(App.dsm.packOtherdata(), "otherdata.json");
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
