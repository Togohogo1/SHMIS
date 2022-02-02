package com.company.pages;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.company.pages.program.AppointmentBooking;
import com.company.pages.program.MyAppointments;
import com.company.pages.program.PatientCheckinQueue;
import com.company.pages.program.PatientIndex;
import com.company.pages.program.Profile;
import com.company.pages.program.WeeklyCalendar;

public class Program extends JPanel {
    private JTabbedPane patientTabs;
    private JTabbedPane employeeTabs;

    private AppointmentBooking appointmentBooking;
    private MyAppointments myAppointments;
    private PatientCheckinQueue patientCheckinQueue;
    private PatientIndex patientIndex;
    private Profile profile, profile2;
    private WeeklyCalendar weeklyCalendar, weeklyCalendar2;

    public Program() {
        // Initialize components
        patientTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        employeeTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);

        appointmentBooking = new AppointmentBooking();
        myAppointments = new MyAppointments();
        patientCheckinQueue = new PatientCheckinQueue();
        patientIndex = new PatientIndex();
        profile = new Profile();
        profile2 = new Profile();  // Same instance referenced by other JTabbedPane removed from the frist
        weeklyCalendar = new WeeklyCalendar();
        weeklyCalendar2 = new WeeklyCalendar();

        // Patient
        appendTab(patientTabs, appointmentBooking, "Booking", 0);
        appendTab(patientTabs, myAppointments, "Appointments", 1);
        appendTab(patientTabs, weeklyCalendar, "Calendar", 2);
        appendTab(patientTabs, profile, "Profile", 3);

        // Employee
        appendTab(employeeTabs, patientCheckinQueue, "Check-In", 0);
        appendTab(employeeTabs, patientIndex, "Patient Index", 1);
        appendTab(employeeTabs, weeklyCalendar2, "Calendar", 2);
        appendTab(employeeTabs, profile2, "Profile", 3);

        this.setLayout(new GridLayout(1, 1));

        // if current logged in is a patient
        this.add(patientTabs);
        // else
        // this.add(employeeTabs);
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
