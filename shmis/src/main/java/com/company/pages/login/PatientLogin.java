package com.company.pages.login;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.company.pages.Settings;

public class PatientLogin extends JPanel {
    private JLabel email;  // doesn't need to be at this scope
    private JLabel password;  // doesn't need to be at this scope

    private JTextField emailInput;
    private JPasswordField passwordInput;

    private JButton register;
    private JButton login;

    public PatientLogin() {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        email = new JLabel("Email");
        password = new JLabel("Password");

        emailInput = new JTextField();
        passwordInput = new JPasswordField();

        register = new JButton("Register");
        login = new JButton("Login");

        // Setting sizes

        // Positioning
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        this.add(email, c);

        c.gridx = 1;
        c.gridy = 0;
        this.add(emailInput, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(password, c);

        c.gridx = 1;
        c.gridy = 1;
        this.add(passwordInput, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(login, c);

        c.gridx = 1;
        c.gridy = 2;
        this.add(register, c);
    }
}
