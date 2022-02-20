package com.company.pages;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.company.App;
import com.company.pages.login.EmployeeLogin;
import com.company.pages.login.PatientLogin;

public class Login extends JPanel {
    private JTabbedPane tabs;
    private PatientLogin patientLogin;
    private EmployeeLogin employeeLogin;

    public Login() {
        tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        patientLogin = new PatientLogin();
        employeeLogin = new EmployeeLogin();

        appendTab(patientLogin, "Patient", 0);
        appendTab(employeeLogin, "Employee", 1);

        this.setLayout(new GridLayout(1, 1));
        this.add(tabs);
    }

    public void appendTab(JPanel panel, String name, int idx) {
        JLabel label = new JLabel(name);
        label.setFont(Settings.H1_BOLD);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(256, 50));

        tabs.addTab("", panel);
        tabs.setTabComponentAt(idx, label);
    }
}
