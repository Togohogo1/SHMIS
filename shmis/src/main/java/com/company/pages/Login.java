package com.company.pages;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Login extends JPanel {
    private JPanel sidebar;
    private JPanel login;
    private JButton patient, employee, guest;

    public Login() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.gray);
    }

    public JPanel sidebar() {
        sidebar = new JPanel(new GridBagLayout());
        sidebar.setBackground(Color.decode("#A9E3F5"));

        GridBagConstraints c = new GridBagConstraints();

        patient = new JButton("Patient");
        patient.setForeground(Color.WHITE);
        patient.setBackground(Color.decode("#009BCC"));
        patient.setHorizontalAlignment(SwingConstants.LEFT);
        patient.setFont(new Font("Roboto", Font.PLAIN, 18));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.weighty = 1;
        sidebar.add(patient, c);

        employee = new JButton("Employee");
        employee.setForeground(Color.WHITE);
        employee.setBackground(Color.decode("#009BCC"));
        employee.setHorizontalAlignment(SwingConstants.LEFT);
        employee.setFont(new Font("Roboto", Font.PLAIN, 18));
        c.gridy = 1;
        sidebar.add(employee, c);

        guest = new JButton("Guest");
        guest.setForeground(Color.WHITE);
        guest.setBackground(Color.decode("#009BCC"));
        guest.setHorizontalAlignment(SwingConstants.LEFT);
        guest.setFont(new Font("Roboto", Font.PLAIN, 18));
        c.gridy = 2;
        c.insets = new Insets(0, 0, 450, 0);
        sidebar.add(guest, c);

        return sidebar;
    }

    public JPanel login() {
        login = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        return login;
    }
}
