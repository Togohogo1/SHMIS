package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Booking extends JPanel {
    private JDialog self;

    private JRadioButton[] xRay;
    private JRadioButton[] ultrasound;

    private JComboBox<String> day;
    private JComboBox<String> from;
    private JComboBox<String> to;
    private JComboBox<String> referralDoctor;

    private JTextField notes;

    public Booking(JDialog self) {
        this.self = self;

        // Initializing the elemetns


    }

    public JPanel initXRay() {
        JPanel panel = new JPanel();
        return panel;
    }

    public JPanel initUltrasound() {
        JPanel panel = new JPanel();
        return panel;
    }

    public JPanel initOther() {
        JPanel panel = new JPanel();
        return panel;
    }
}
