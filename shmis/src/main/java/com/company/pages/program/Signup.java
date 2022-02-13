package com.company.pages.program;

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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.ColorAttribute;

import com.company.classes.Patient;

public class Signup extends JPanel implements ActionListener {
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
        new JLabel("Birthdate:"),
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

        inputs = new JTextField[10];

        for (int i = 0; i < 10; i++) {
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
        panel.add(labels[7], ci);

        ci.gridx = 1;
        ci.gridy = 0;
        panel.add(inputs[7], ci);

        ci.gridx = 0;
        ci.gridy = 1;
        panel.add(labels[5], ci);

        ci.gridx = 1;
        ci.gridy = 1;
        panel.add(inputs[5], ci);

        return panel;
    }

    public JPanel initOther() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.CYAN);        // DEBUG
        panel.setPreferredSize(new Dimension(400, 300));        // DEBUG
        GridBagConstraints ci = new GridBagConstraints();

        ci.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 2; i++) {
            ci.gridy = 0;

            for (int j = 0; j < 10; j++) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validPatient()) {
            createPatient();// database stuff
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean validPatient() {
        boolean isValid = true;

        try {
            long age = Long.valueOf(inputs[0].getText());

            if (age < 0 || age > 150)
                throw new Exception();

        } catch (Exception e) {
            errorMessage = "Please input an age between 0 and 150";
            return false;
        }

        return true;
    }

    public Patient createPatient() {
        // DEBUG
        for (int i = 0; i < 10; i++) {
            System.out.println(inputs[i].getText());
        }

        ArrayList<Long> appointments = new ArrayList<>();
        Patient patient = new Patient(
            Long.valueOf(inputs[0].getText()),
            inputs[1].getText(),
            inputs[2].getText(),
            inputs[3].getText(),
            inputs[4].getText(),
            inputs[5].getText(),
            inputs[6].getText(),
            inputs[7].getText(),
            inputs[8].getText(),
            inputs[9].getText(),
            appointments
        );

        return null;
        // return patient;
        // TODO make the function voic and manage the database here?
    }
}
