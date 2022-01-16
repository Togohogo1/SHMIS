package com.company.pages;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.company.pages.login.EmployeeLogin;
import com.company.pages.login.GuestLogin;
import com.company.pages.login.PatientLogin;

public class Login extends JPanel {
    public JTabbedPane tabs;
    public PatientLogin patientLogin;
    public EmployeeLogin employeeLogin;
    public GuestLogin guestLogin;

    public Login() {
        tabs = new JTabbedPane();
        patientLogin = new PatientLogin();
        employeeLogin = new EmployeeLogin();
        guestLogin = new GuestLogin();
    }

    public void addTab(JPanel panel) {

    }
}
