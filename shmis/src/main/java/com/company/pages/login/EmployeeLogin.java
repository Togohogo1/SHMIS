package com.company.pages.login;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Employee;

public class EmployeeLogin extends JPanel implements ActionListener {
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
        login.addActionListener(this);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputKey = keyInput.getText();
        String inputPassword = String.valueOf(passwordInput.getPassword());  // .getText() deprecated for JPasswordField

        if (!inputKey.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Invalid Employee Key", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!inputPassword.equals("password")) {
            JOptionPane.showMessageDialog(null, "Incorrect Password", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        App.dsm.setCurrentUser(new Employee());
        App.shmis.LoggedIn();
        JOptionPane.showMessageDialog(null, "Successfully Logged in as Employee");
    };
}
