package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AppointmentBooking extends JPanel {
    public AppointmentBooking() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel topLeft = new JPanel(new BorderLayout());
        JPanel topRight = new JPanel(new BorderLayout());
        JPanel bottomLeft = new JPanel(new BorderLayout());
        JPanel bottomRight = new JPanel(new BorderLayout());

        topLeft.setBackground(Color.BLACK);
        topRight.setBackground(Color.BLUE);
        bottomLeft.setBackground(Color.RED);
        bottomRight.setBackground(Color.GREEN);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.45;
        this.add(topLeft, c);


        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0.45;
        this.add(topRight, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.55;
        this.add(bottomLeft, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 0.55;
        this.add(bottomRight, c);
    }
}
