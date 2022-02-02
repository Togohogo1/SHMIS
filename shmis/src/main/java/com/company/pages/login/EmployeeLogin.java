package com.company.pages.login;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EmployeeLogin extends JPanel {
    private JLabel key;
    private JLabel password;

    private JTextField keyInput;
    private JPasswordField passwordInput;

    private JButton login;

    public EmployeeLogin() {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        key = new JLabel("Key");
        password = new JLabel("Password");

        keyInput = new JTextField();
        passwordInput = new JPasswordField();

        login = new JButton("Login");

        // Setting sizes

        // Positioning
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(key, c);

        c.gridx = 1;
        c.gridy = 0;
        this.add(keyInput, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(password, c);

        c.gridx = 1;
        c.gridy = 1;
        this.add(passwordInput, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        this.add(login, c);
    };
}
