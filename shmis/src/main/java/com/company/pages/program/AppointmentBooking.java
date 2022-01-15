package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AppointmentBooking extends JPanel {
    public AppointmentBooking() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel topLeft = new JPanel(new BorderLayout());
        JPanel topRight = new JPanel(new BorderLayout());
        JPanel bottomLeft = new JPanel(new BorderLayout());
        JPanel bottomRight = new JPanel(new BorderLayout());

        JLabel xray = new JLabel("X-Ray", SwingConstants.CENTER);
        xray.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
        xray.setForeground(Color.WHITE);
        xray.setBorder(new EmptyBorder(5, 5, 5, 5));
        xray.setBackground(Color.DARK_GRAY);
        xray.setOpaque(true);

        JLabel dateAndTime = new JLabel("Date and Time", SwingConstants.CENTER);
        dateAndTime.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
        dateAndTime.setForeground(Color.WHITE);
        dateAndTime.setBorder(new EmptyBorder(5, 5, 5, 5));
        dateAndTime.setBackground(Color.DARK_GRAY);
        dateAndTime.setOpaque(true);

        JLabel ultrasound = new JLabel("Ultrasound", SwingConstants.CENTER);
        ultrasound.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
        ultrasound.setForeground(Color.WHITE);
        ultrasound.setBorder(new EmptyBorder(5, 5, 5, 5));
        ultrasound.setBackground(Color.DARK_GRAY);
        ultrasound.setOpaque(true);

        JLabel other = new JLabel("Other", SwingConstants.CENTER);
        other.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
        other.setForeground(Color.WHITE);
        other.setBorder(new EmptyBorder(5, 5, 5, 5));
        other.setBackground(Color.DARK_GRAY);
        other.setOpaque(true);

        JPanel xrayContents = new JPanel(new GridLayout(6, 2));

        JRadioButton abdomen = new JRadioButton("Abdomen");
        abdomen.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        abdomen.setBorder(new EmptyBorder(10, 10, 10, 10));

        JRadioButton spineAndPelvis = new JRadioButton("Spine and Pelvis");
        spineAndPelvis.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        spineAndPelvis.setBorder(new EmptyBorder(10, 10, 10, 10));

        JRadioButton chest = new JRadioButton("Chest");
        chest.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        chest.setBorder(new EmptyBorder(10, 10, 10, 10));

        JRadioButton upperExtremeties = new JRadioButton("Upper Extremeties");
        upperExtremeties.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        upperExtremeties.setBorder(new EmptyBorder(10, 10, 10, 10));

        JRadioButton headAndNeck = new JRadioButton("Head and Neck");
        headAndNeck.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        headAndNeck.setBorder(new EmptyBorder(10, 10, 10, 10));

        JRadioButton lowerExtremeties = new JRadioButton("Lower Extremeties");
        lowerExtremeties.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        lowerExtremeties.setBorder(new EmptyBorder(10, 10, 10, 10));

        JRadioButton skeletal = new JRadioButton("Skeletal");
        skeletal.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        skeletal.setBorder(new EmptyBorder(10, 10, 10, 10));

        xrayContents.add(abdomen);
        xrayContents.add(spineAndPelvis);
        xrayContents.add(chest);
        xrayContents.add(upperExtremeties);
        xrayContents.add(headAndNeck);
        xrayContents.add(lowerExtremeties);
        xrayContents.add(skeletal);
        // xrayContents.add(abdomen);
        // xrayContents.add(abdomen);
        // xrayContents.add(abdomen);
        // xrayContents.add(abdomen);
        // xrayContents.add(new JRadioButton("choix 1"));
        // xrayContents.add(new JRadioButton("choix 2"));
        // xrayContents.add(new JRadioButton("choix 3"));
        // xrayContents.add(new JRadioButton("choix 4"));
        // topLeft.setBackground(Color.CYAN);
        // topRight.setBackground(Color.BLUE);
        // bottomLeft.setBackground(Color.RED);
        // bottomRight.setBackground(Color.GREEN);

        JPanel dateAndTimeContents = new JPanel();
        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        dateAndTimeContents.setLayout(new BoxLayout(dateAndTimeContents, BoxLayout.PAGE_AXIS));
        JComboBox day = new JComboBox(petStrings);
        // day.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        // day.setBorder(new EmptyBorder(10, 10, 10, 10));

        JComboBox from = new JComboBox();
        JComboBox to = new JComboBox();

        dateAndTimeContents.add(day);
        dateAndTimeContents.add(from);
        dateAndTimeContents.add(to);

        topLeft.add(xray, BorderLayout.NORTH);
        topLeft.add(xrayContents, BorderLayout.CENTER);

        topRight.add(dateAndTime, BorderLayout.NORTH);
        topRight.add(dateAndTimeContents, BorderLayout.CENTER);

        bottomLeft.add(ultrasound, BorderLayout.NORTH);
        bottomRight.add(other, BorderLayout.NORTH);

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
