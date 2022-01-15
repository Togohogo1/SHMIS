package com.company.pages;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import com.company.pages.login.PatientLogin;
import com.company.pages.program.AppointmentBooking;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class Program extends JPanel {
    public Program() {
        this.setLayout(new GridLayout(1, 1));
        this.add(patientPage());
    }

    public JTabbedPane patientPage() {
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);

        // JPanel dummy = new JPanel();
        // dummy.add(new JButton("click me1"));
        // JPanel dummy2 = new JPanel();
        // dummy2.add(new JButton("click me2"));
        // JPanel dummy3 = new JPanel();
        // dummy3.add(new JButton("click me3"));

        tabs.add("", new AppointmentBooking());
        tabs.add("", new JPanel());
        tabs.add("", new JPanel());

        JLabel l1 = new JLabel("Booking");
        l1.setFont(new Font("Roboto", Font.BOLD, 20));
        l1.setHorizontalAlignment(SwingConstants.LEFT);
        l1.setPreferredSize(new Dimension(256, 50));

        JLabel l2 = new JLabel("Appointments");
        l2.setFont(new Font("Roboto", Font.BOLD, 20));
        l2.setHorizontalAlignment(SwingConstants.LEFT);
        l2.setPreferredSize(new Dimension(256, 50));

        JLabel l3 = new JLabel("Calendar");
        l3.setFont(new Font("Roboto", Font.BOLD, 20));
        l3.setHorizontalAlignment(SwingConstants.LEFT);
        l3.setPreferredSize(new Dimension(256, 50));


        tabs.setTabComponentAt(0, l1);
        tabs.setTabComponentAt(1, l2);
        tabs.setTabComponentAt(2, l3);

        return tabs;
    }
}
