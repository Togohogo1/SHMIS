package com.company.pages;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.company.App;
import com.company.pages.program.MyAppointments;
import com.company.pages.program.PatientCheckinQueue;
import com.company.pages.program.PatientIndex;
import com.company.pages.program.Profile;
import com.company.pages.program.WeeklyCalendar;

public class Program extends JPanel {
    private JTabbedPane patientTabs;
    private JTabbedPane employeeTabs;

    private MyAppointments myAppointments;
    private PatientCheckinQueue patientCheckinQueue;
    private PatientIndex patientIndex;
    private Profile profile, profile2;
    private WeeklyCalendar weeklyCalendar, weeklyCalendar2;

    public Program() {
        // Initialize components
        patientTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        employeeTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);

        myAppointments = new MyAppointments();
        patientCheckinQueue = new PatientCheckinQueue();
        patientIndex = new PatientIndex();
        profile = new Profile();
        profile2 = new Profile();  // Same instance referenced by other JTabbedPane removed from the frist
        weeklyCalendar = new WeeklyCalendar();
        weeklyCalendar2 = new WeeklyCalendar();  // TODO another constructor that allows for disabling for patients?

        appendTab(patientTabs, myAppointments, "Appointments", 0);
        appendTab(patientTabs, weeklyCalendar, "Calendar", 1);
        appendTab(patientTabs, profile, "Profile", 2);

        appendTab(employeeTabs, patientCheckinQueue, "Check-In", 0);
        appendTab(employeeTabs, patientIndex, "Patient Index", 1);
        appendTab(employeeTabs, weeklyCalendar2, "Calendar", 2);
        appendTab(employeeTabs, profile2, "Profile", 3);

        this.setLayout(new GridLayout(1, 1));

        if (App.dsm.getCurrentUser().getDesignation().equals("Patient"))  // Patient
            this.add(patientTabs);
        else  // Employee
            this.add(employeeTabs);
    }

    public void appendTab(JTabbedPane tabs, JPanel panel, String name, int index) {
        JLabel label = new JLabel(name);
        label.setFont(Settings.H1_BOLD);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(256, 50));

        tabs.addTab("", panel);
        tabs.setTabComponentAt(index, label);
    }
}
