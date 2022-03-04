package com.company.pages;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.company.pages.login.EmployeeLogin;
import com.company.pages.login.PatientLogin;
import com.company.utilities.FontColor;

/**
 * The login page for patients and employees.
 */
public class Login extends JPanel {
    private JTabbedPane tabs;
    private PatientLogin patientLogin;
    private EmployeeLogin employeeLogin;

    /**
     * Initializes the login page, which functions through tabbed panes.
     */
    public Login() {
        tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        patientLogin = new PatientLogin();
        employeeLogin = new EmployeeLogin();

        appendTab(patientLogin, "Patient", 0);
        appendTab(employeeLogin, "Employee", 1);

        this.setLayout(new GridLayout(1, 1));  // Easy way to ensure the whole frame gets filled
        this.add(tabs);
    }

    /**
     * Appends a new tab to the login <code>JTabbedPane</code>.
     *
     * @param panel The panel to be added
     * @param name The name of the tab
     * @param idx The index of the tab
     */
    public void appendTab(JPanel panel, String name, int idx) {
        JLabel label = new JLabel(name);
        label.setFont(FontColor.H1_BOLD);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(256, 50));

        tabs.addTab("", panel);
        tabs.setTabComponentAt(idx, label);
    }
}
