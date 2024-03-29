package com.company.pages.login;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Patient;
import com.company.utilities.FontColor;
import com.company.utilities.PopupHelper;

/**
 * Page for patient login.
 */
public class PatientLogin extends JPanel implements ActionListener {
    private JLabel email;
    private JTextField emailInput;

    private JLabel password;
    private JPasswordField passwordInput;

    private JButton register;
    private JButton login;

    // For Register popup
    private JDialog signup;
    private JTextField[] inputs;
    private JButton registerConfirm;

    /**
     * Initializes the patient login page.
     */
    public PatientLogin() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        email = new JLabel("Enter email:");
        emailInput = new JTextField();

        password = new JLabel("Enter password:");
        passwordInput = new JPasswordField();

        register = new JButton("Register");
        register.addActionListener(this);
        login = new JButton("Login");
        login.addActionListener(this);

        // Setting sizes and styling
        email.setFont(FontColor.H2_BOLD);
        email.setPreferredSize(new Dimension(120, 30));
        emailInput.setFont(FontColor.H2);
        emailInput.setPreferredSize(new Dimension(120, 30));

        password.setFont(FontColor.H2_BOLD);
        password.setPreferredSize(new Dimension(120, 30));
        passwordInput.setFont(FontColor.H2);
        passwordInput.setPreferredSize(new Dimension(120, 30));

        register.setFont(FontColor.H2);
        register.setPreferredSize(new Dimension(120, 30));
        login.setFont(FontColor.H2);
        login.setPreferredSize(new Dimension(120, 30));

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

    /**
     * Creates the patient register popup.
     *
     * @return The patient register popup
     */
    public JPanel createSignup() {
        // Initializing the JPanel to be returned
        JPanel popup = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        JLabel[] labels = {
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
        inputs = new JTextField[9];
        registerConfirm = new JButton("Register");
        registerConfirm.addActionListener(this);

        // Setting sizes and styling
        for (int i = 0; i < 9; i++) {
            labels[i].setPreferredSize(new Dimension(75, 22)); // Default JButton size
            inputs[i] = new JTextField();
            inputs[i].setPreferredSize(new Dimension(100, 22));
        }

        // Positioning
        c.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                c.gridx = i;
                c.gridy = j;
                popup.add((i == 0 ? labels[j] : inputs[j]), c);
            }
        }

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(15, 0, 0, 0);
        popup.add(registerConfirm, c);

        return popup;
    }

    /**
     * Creates a patient based on register information.
     *
     * @return A patient based on register information
     */
    public Patient createPatient() {
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
            appointments
        );

        return patient;
    }

    /**
     * Get patient from patient list after successful login or register.
     *
     * @param email Valid email of patient
     * @return The corresponding patient
     */
    public Patient getPatient(String email) {
        for (Patient p : App.dsm.getPatientList()) {
            if (p.getEmail().equals(email)) // All emails are unique
                return p;
        }

        return null;
    }

    /**
     * Checks if the patient's password is correct or not when logging in.
     *
     * @param patient  The patient thats logged in
     * @param password The inputted password in the password input box
     * @return <code>True</code> if the patient's password is correct
     */
    public boolean passwordCorrect(Patient patient, String password) {
        return patient.getPassword().equals(password);
    }

    /**
     * Performing actions when clicked on the register, login, or confirm register
     * buttons.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            signup = new JDialog(null, "Register", JDialog.ModalityType.APPLICATION_MODAL);
            signup.add(createSignup());
            signup.setSize(new Dimension(350, 450));
            signup.setLocationRelativeTo(null);
            signup.setResizable(false);
            signup.setVisible(true);

        } else if (e.getSource() == login) {
            String inputEmail = emailInput.getText().toLowerCase();
            String inputPassword = String.valueOf(passwordInput.getPassword()); // .getText() deprecated for JPasswordField
            Patient inputPatient = getPatient(inputEmail);

            if (inputPatient == null) {
                JOptionPane.showMessageDialog(null, "Email does not exist", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!passwordCorrect(inputPatient, inputPassword)) {
                JOptionPane.showMessageDialog(null, "Incorrect password", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            App.dsm.setCurrentUser(inputPatient);
            App.shmis.LoggedIn();

            // Clearing text so it won't appear when logged out
            emailInput.setText("");
            passwordInput.setText("");

        } else if (e.getSource() == registerConfirm) {
            if (PopupHelper.validPatient(inputs, "")) {
                Patient registerPatient = createPatient();

                // Database functions
                App.dsm.getPatientList().add(registerPatient);
                App.dsm.setCurrentUser(registerPatient);
                App.shmis.LoggedIn();

                signup.setVisible(false);

            } else
                JOptionPane.showMessageDialog(null, PopupHelper.getError(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
