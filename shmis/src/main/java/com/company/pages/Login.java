package com.company.pages;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Login extends JPanel {
    public Login() {
        // JLabel lb = new JLabel();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.gray);
        // GridBagConstraints c = new GridBagConstraints();
        // this.setSize(200, 540);

        // JPanel topButtons = new JPanel();
        // topButtons.setLayout(new BoxLayout(topButtons, BoxLayout.Y_AXIS));
        // JButton button1 = new JButton("button 1");
        // JButton button2 = new JButton("button 2");
        // JButton button3 = new JButton("button 3");
        // topButtons.add(button1);
        // topButtons.add(button2);
        // topButtons.add(button3);
        // this.add(topButtons, BorderLayout.NORTH);


        // JPanel bottomButtons = new JPanel();
        // bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.Y_AXIS));
        JButton button4 = new JButton("button 4");
        // bottomButtons.add(button4);
        // this.add(bottomButtons, BorderLayout.SOUTH);
        // this.add(button4);
    }
}
