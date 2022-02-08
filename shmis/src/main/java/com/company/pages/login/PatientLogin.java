package com.company.pages.login;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Patient;
import com.company.pages.Settings;

public class PatientLogin extends JPanel implements ActionListener {
    private JLabel email;  // doesn't need to be at this scope
    private JLabel password;  // doesn't need to be at this scope

    private JTextField emailInput;  // TODO dont make this thing resize to text later
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
        register.addActionListener(this);
        login = new JButton("Login");
        login.addActionListener(this);

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

    public Patient getPatient(String email) {
        for (Patient p : App.dsm.getPatientList()) {
            if (p.getEmail().equals(email))  // All emails are unique
                return p;
        }

        return null;
    }

    public boolean passwordCorrect(Patient patient, String password) { // think of some encryption system
        return patient.getPassword().equals(password);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register)
            ;
        else if (e.getSource() == login) {
            String inputEmail = emailInput.getText();
            String inputPassword = String.valueOf(passwordInput.getPassword());  // .getText() deprecated for JPasswordField
            Patient inputPatient = getPatient(inputEmail);

            if (inputPatient == null) {
                JOptionPane.showMessageDialog(null, "Email Does Not Exist", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!passwordCorrect(inputPatient, inputPassword)) {
                JOptionPane.showMessageDialog(null, "Incorrect Password", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            App.dsm.setCurrentUser(inputPatient);
            App.shmis.LoggedIn();
            JOptionPane.showMessageDialog(null, "Successfully Logged in as Patient");
        }
    }
}
