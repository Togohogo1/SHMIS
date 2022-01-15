package com.company.pages.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PatientLogin extends JPanel {
    public PatientLogin() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label1 = new JLabel("Email");
        JLabel label2 = new JLabel("Password");;

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JButton but = new JButton("Login");
        JButton but2 = new JButton("Register");
        but.setPreferredSize(new Dimension(178, 50));
        but2.setPreferredSize(new Dimension(178, 50));
        but.setFont(new Font("Roboto", Font.PLAIN, 20));
        but2.setFont(new Font("Roboto", Font.PLAIN, 20));


        username.setFont(new Font("Roboto", Font.PLAIN, 20));
        password.setFont(new Font("Roboto", Font.PLAIN, 20));
        username.setMargin(new Insets(10, 10, 10, 10));
        password.setMargin(new Insets(10, 10, 10, 10));
        username.setPreferredSize(new Dimension(256, 50));
        password.setPreferredSize(new Dimension(256, 50));

        JLabel l2 = new JLabel("Email");
        l2.setFont(new Font("Roboto", Font.PLAIN, 20));
        // l2.setHorizontalAlignment(SwingConstants.LEFT);
        l2.setPreferredSize(new Dimension(100, 50));

        JLabel l3 = new JLabel("Password");
        l3.setFont(new Font("Roboto", Font.PLAIN, 20));
        // l3.setHorizontalAlignment(SwingConstants.LEFT);
        l3.setPreferredSize(new Dimension(100, 50));

        JPanel bruh = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        // bruh.
        // bruh.setLayout(new );
        bruh.add(but);
        bruh.add(Box.createRigidArea(new Dimension(10, 0)));
        bruh.add(but2);
        // this.add(l2, c);

        // this.add(bruh, c);

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(l2, c);

        c.gridx = 1;
        c.gridy = 0;
        this.add(username, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(l3, c);

        c.gridx = 1;
        c.gridy = 1;
        this.add(password, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(bruh, c);
    }
}
