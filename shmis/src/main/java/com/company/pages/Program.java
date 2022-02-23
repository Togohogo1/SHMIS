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

public class Program extends JPanel implements ChangeListener {
    private JTabbedPane patientTabs;
    private JTabbedPane employeeTabs;

    private MyAppointments myAppointments;  // TODO need to be up here?
    private PatientQueue patientCheckinQueue;
    private PatientIndex patientIndex;
    private ProfileLogout profile, profile2;
    private WeeklyCalendar weeklyCalendar, weeklyCalendar2;

    public Program() {
        // Initialize components
        patientTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        patientTabs.addChangeListener(this);
        employeeTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        employeeTabs.addChangeListener(this);

        this.setLayout(new GridLayout(1, 1));

        if (App.dsm.currUserIsPatient()) {  // Patient
            myAppointments = new MyAppointments();
            profile = new ProfileLogout();
            weeklyCalendar = new WeeklyCalendar();
            appendTab(patientTabs, myAppointments, "Appointments", 0);  // TODO or do i even need this unecessary initializtion (Unless i plan to do something with jtabbedpanes listener)
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

    public void appendTab(JTabbedPane tabs, JPanel panel, String name, int idx) {
        JLabel label = new JLabel(name);
        label.setFont(Settings.H1_BOLD);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(256, 50));

        tabs.addTab("", panel);
        tabs.setTabComponentAt(idx, label);
    }

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
