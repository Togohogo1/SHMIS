package com.company.pages;

import java.awt.Dimension;

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
    public JTabbedPane patientTabs;
    public JTabbedPane employeeTabs;

    public AppointmentBooking appointmentBooking;
    public MyAppointments myAppointments;
    public PatientCheckinQueue patientCheckinQueue;
    public PatientIndex patientIndex;
    public Profile profile;
    public WeeklyCalendar weeklyCalendar;

    public Program() {
        patientTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
        employeeTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
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
