package com.company.pages.program;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.JDBCType;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.ColorAttribute;

import com.company.App;
import com.company.classes.Patient;

public class Signup extends JPanel implements ActionListener {
    private final int AGE = 0;
    private final int FIRST_NAME = 1;
    private final int LAST_NAME = 2;
    private final int GENDER = 3;
    private final int ID = 4;
    private final int PASSWORD = 5;
    private final int ADDRESS = 6;
    private final int EMAIL = 7;
    private final int TELEPHONE = 8;

    private String errorMessage;
    private JButton register;
    private JLabel[] labels = {
        new JLabel("Age:"),
        new JLabel("First Name:"),
        new JLabel("Last Name:"),
        new JLabel("Gender:"),
        new JLabel("ID:"),
        new JLabel("Password:"),
        new JLabel("Address:"),
        new JLabel("Email:"),
        new JLabel("Telephone:"),
    };
    private JTextField[] inputs;

    public Signup() {
        super(new GridBagLayout());
        GridBagConstraints co = new GridBagConstraints();

        // Initializing the elements
        JTabbedPane top = new JTabbedPane();

        register = new JButton("Register");
        register.addActionListener(this);

        inputs = new JTextField[9];

        for (int i = 0; i < 9; i++) {
            inputs[i] = new JTextField();
        }

        // Setting sizes and styling

        // Positioning
        top.addTab("Login Credentials", initCredentials());
        top.addTab("Other Info", initOther());

        co.gridy = 0;
        this.add(top, co);

        co.gridy = 1;
        this.add(register, co);
    }

    public JPanel initCredentials() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.cyan);        // DEBUG
        GridBagConstraints ci = new GridBagConstraints();

        ci.insets = new Insets(5, 5, 5, 5);
        ci.gridx = 0;
        ci.gridy = 0;
        panel.add(labels[EMAIL], ci);

        ci.gridx = 1;
        ci.gridy = 0;
        panel.add(inputs[EMAIL], ci);

        ci.gridx = 0;
        ci.gridy = 1;
        panel.add(labels[PASSWORD], ci);

        ci.gridx = 1;
        ci.gridy = 1;
        panel.add(inputs[PASSWORD], ci);

        return panel;
    }

    public JPanel initOther() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 300));        // DEBUG
        GridBagConstraints ci = new GridBagConstraints();

        ci.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 2; i++) {
            ci.gridy = 0;

            for (int j = 0; j < 9; j++) {
                ci.gridx = i;

                if (j != 5 && j != 7) {
                    System.out.println(j);
                    panel.add((i == 0 ? labels[j] : inputs[j]), ci);
                    ci.gridy++;
                }
            }
        }

        return panel;
    }

    public boolean validPatient() {
        // No input check
        if (inputs[FIRST_NAME].getText().trim().equals("") ||
            inputs[LAST_NAME].getText().trim().equals("") ||
            inputs[ID].getText().trim().equals("") ||
            inputs[PASSWORD].getText().trim().equals("") ||
            inputs[ADDRESS].getText().trim().equals("")) {
            errorMessage = "Please fill in all fields";
            return false;
        }

        // Age check
        try {
            long age = Long.valueOf(inputs[AGE].getText());

            if (age < 0 || age > 150)
                throw new Exception();

        } catch (Exception e) {
            errorMessage = "Please input an age between 0 and 150";
            return false;
        }

        // Gender check
        String gender = inputs[GENDER].getText().toLowerCase();

        if (!gender.equals("m") && !gender.equals("f") && !gender.equals("o")) {
            errorMessage = "Please input a proper gender: male (M), female (F), or other (O)";
            return false;
        }


        // Email regex check
        if (!Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+").matcher(inputs[EMAIL].getText()).matches()) {
            errorMessage = "Please input a proper email";
            return false;
        }

        // Telephone regex check
        if (!Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}").matcher(inputs[TELEPHONE].getText()).matches()) {
            errorMessage = "Please input a proper telephone number";
            return false;
        }

        return true;
    }

    public Patient createPatient() {
        // DEBUG
        for (int i = 0; i < 9; i++) {
            System.out.println(inputs[i].getText());
        }

        ArrayList<Long> appointments = new ArrayList<>();
        Patient patient = new Patient(
            Long.valueOf(inputs[AGE].getText()),
            inputs[FIRST_NAME].getText(),
            inputs[LAST_NAME].getText(),
            inputs[GENDER].getText(),
            inputs[ID].getText(),
            inputs[PASSWORD].getText(),
            inputs[ADDRESS].getText(),
            inputs[EMAIL].getText(),
            inputs[TELEPHONE].getText(),
            appointments
        );

        return patient;
        // TODO make the function voic and manage the database here?
    }

    public boolean emailExists(String email) {
        for (Patient p : App.dsm.getPatientList()) {
            if (p.getEmail().equals(email))
                return false;
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validPatient()) {
            createPatient();// database stuff
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
