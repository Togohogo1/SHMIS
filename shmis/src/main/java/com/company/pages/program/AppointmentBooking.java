package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AppointmentBooking extends JPanel {
    private JRadioButton[] XRay;
    private JRadioButton[] ultrasound;

    private JComboBox<String> dayDropdown;
    private JComboBox<String> fromDropdown;
    private JComboBox<String> toDropdown;
    private JComboBox<String> doctorDropdown;

    private JTextField notesInput;
    private JButton bookAppointment;

    public AppointmentBooking() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
    }

    public JPanel initTopLeft() {
        JPanel topLeft = new JPanel(new BorderLayout());
        JLabel tab = new JLabel("X-Ray");  // Top part of panel
        JPanel xrayContents = new JPanel(new GridLayout(6, 2));  // Bottom part of panel

        String[] XRayNames = {"Abdomen", "Spine and Pelvis", "Chest", "Upper Extremeties", "Head and Neck", "Lower Extremeties", "Skeletal"};
        XRay = new JRadioButton[7];

        for (int i = 0; i < 7; i++) {
            XRay[i] = new JRadioButton(XRayNames[i]);
            xrayContents.add(XRay[i]);
        }

        topLeft.add(tab, BorderLayout.NORTH);
        topLeft.add(xrayContents, BorderLayout.SOUTH);

        return topLeft;
    }

    public JPanel initTopRight() {
        JPanel topRight = new JPanel(new BorderLayout());
        JLabel tab = new JLabel("Date and Time");
        JPanel dateContents = new JPanel(new GridBagLayout());
        JLabel day = new JLabel("Day");
        JLabel from = new JLabel("From");
        JLabel to = new JLabel("To");

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        dayDropdown = new JComboBox<>(days);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        dateContents.add(day, c);

        c.gridx = 1;
        c.gridy = 0;
        dateContents.add(dayDropdown, c);

        c.gridx = 0;
        c.gridy = 1;
        dateContents.add(from, c);


        topRight.add(tab, BorderLayout.NORTH);
        topRight.add(dateContents, BorderLayout.SOUTH);

        return topRight;
    }

    public JPanel initBottomLeft() {
        JPanel bottomLeft = new JPanel(new BorderLayout());
        JLabel tab = new JLabel("Ultrasound");  // Top part of panel
        JPanel ultrasoundContents = new JPanel(new GridLayout(4, 2));  // Bottom part of panel

        String[] ultrasoundNames = {"Genereal", "Obstetrical", "Musckuloskeletal", "Cardiovascular"};
        ultrasound = new JRadioButton[4];

        for (int i = 0; i < 4; i++) {
            ultrasound[i] = new JRadioButton(ultrasoundNames[i]);
            ultrasoundContents.add(ultrasound[i]);
        }

        bottomLeft.add(tab, BorderLayout.NORTH);
        bottomLeft.add(ultrasoundContents, BorderLayout.SOUTH);

        return bottomLeft;
    }

    public JPanel initBottomRight() {
        JPanel bottomRight = new JPanel(new BorderLayout());
        JLabel tab = new JLabel("Other");
        JPanel dateContents = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        bottomRight.add(tab, BorderLayout.NORTH);
        bottomRight.add(dateContents, BorderLayout.SOUTH);

        return bottomRight;
    }
}
