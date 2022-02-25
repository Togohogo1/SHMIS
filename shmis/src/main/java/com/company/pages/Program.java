package com.company.pages;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.company.App;
import com.company.pages.program.MyAppointments;
import com.company.pages.program.PatientQueue;
import com.company.pages.program.PatientIndex;
import com.company.pages.program.ProfileLogout;
import com.company.pages.program.WeeklyCalendar;
import com.company.utilities.FontColor;

/**
 * The main program pages for patients and employees.
 */
public class Program extends JPanel implements ChangeListener {
    private JTabbedPane patientTabs;
    private JTabbedPane employeeTabs;

    private MyAppointments myAppointments;
    private PatientQueue patientCheckinQueue;
    private PatientIndex patientIndex;
    private ProfileLogout profile, profile2;
    private WeeklyCalendar weeklyCalendar, weeklyCalendar2;

    /**
     * Initializes the program page, which functions through tabbed panes. Different tabs will be shown depending on the current user type.
     */
    public Program() {
        // Initialize components
        patientTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        patientTabs.addChangeListener(this);
        employeeTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        employeeTabs.addChangeListener(this);

        // Easy way to ensure the whole frame gets filled
        this.setLayout(new GridLayout(1, 1));

        if (App.dsm.currUserIsPatient()) {  // Patient
            myAppointments = new MyAppointments();
            profile = new ProfileLogout();
            weeklyCalendar = new WeeklyCalendar();
            appendTab(patientTabs, myAppointments, "Appointments", 0);
            appendTab(patientTabs, weeklyCalendar, "Calendar", 1);
            appendTab(patientTabs, profile, "Profile and Logout", 2);
            this.add(patientTabs);
        } else { // Employee
            patientCheckinQueue = new PatientQueue();
            patientIndex = new PatientIndex();
            profile2 = new ProfileLogout();  // Same instance referenced by other JTabbedPane removed from the frist
            weeklyCalendar2 = new WeeklyCalendar();
            appendTab(employeeTabs, patientCheckinQueue, "Patient Queue", 0);
            appendTab(employeeTabs, weeklyCalendar2, "Calendar", 1);
            appendTab(employeeTabs, patientIndex, "Patient Index", 2);
            appendTab(employeeTabs, profile2, "Profile and Logout", 3);
            this.add(employeeTabs);
        }
    }

    /**
     * Appends a new tab to a specified tabbed pane.
     *
     * @param tabs The specified tabbed pane
     * @param panel The panel to be added
     * @param name The name of the tab
     * @param idx The index of the tab
     */
    public void appendTab(JTabbedPane tabs, JPanel panel, String name, int idx) {
        JLabel label = new JLabel(name);
        label.setFont(FontColor.H1_BOLD);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(256, 50));

        tabs.addTab("", panel);
        tabs.setTabComponentAt(idx, label);
    }

    /**
     * Detects tab changes. Updates the calendar tab every time the user selects it.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane target = (JTabbedPane) e.getSource();
        int idx = target.getSelectedIndex();

        if (idx == 1) {
            if (App.dsm.currUserIsPatient())
                weeklyCalendar.render();
            else
                weeklyCalendar2.render();
        }
    }
}
