package com.company.pages.login;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Patient;
import com.company.pages.Settings;
// import com.company.pages.program.SignupHelper;
import com.company.pages.program.PopupHelper;

public class PatientLogin extends JPanel implements ActionListener {
    private JLabel email;  // doesn't need to be at this scope
    private JLabel password;  // doesn't need to be at this scope

    private JTextField emailInput;  // TODO dont make this thing resize to text later
    private JPasswordField passwordInput;

    private JButton register;
    private JButton login;

    // For signup popup
    private JDialog signup;
    private JButton registerConfirm;
    private JTextField[] inputs;

    public PatientLogin() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        email = new JLabel("Enter email:");
        password = new JLabel("Enter password:");

        emailInput = new JTextField();
        passwordInput = new JPasswordField();

        register = new JButton("Register");
        register.addActionListener(this);

        login = new JButton("Login");
        login.addActionListener(this);

        // Setting sizes and styling
        email.setFont(Settings.H2_BOLD);
        email.setPreferredSize(new Dimension(120, 30));

        password.setFont(Settings.H2_BOLD);
        password.setPreferredSize(new Dimension(120, 30));

        emailInput.setFont(Settings.H2);
        emailInput.setPreferredSize(new Dimension(120, 30));

        passwordInput.setFont(Settings.H2);
        passwordInput.setPreferredSize(new Dimension(120, 30));

        register.setFont(Settings.H2);
        register.setPreferredSize(new Dimension(120, 30));

        login.setFont(Settings.H2);
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

    public JPanel createSignup() {
        // Initialiing the elements
        JPanel popup = new JPanel(new GridBagLayout());  // To put the stuff in
        GridBagConstraints c = new GridBagConstraints();

        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints ci = new GridBagConstraints();
        inputs = new JTextField[9];

        registerConfirm = new JButton("Register");
        registerConfirm.addActionListener(this);

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

        for (int i = 0; i < 9; i++) {
            inputs[i] = new JTextField();
        }

        // Setting sizes and styling
        for (int i = 0; i < 9; i++) {
            labels[i].setPreferredSize(new Dimension(75, 22));  // Default JButton size
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

        // co.gridy = 0;
        // popup.add(top, co);

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(15, 0, 0, 0);
        popup.add(registerConfirm, c);

        return popup;
    }

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

    public Patient getPatient(String email) {
        for (Patient p : App.dsm.getPatientList()) {
            if (p.getEmail().equals(email))  // All emails are unique
                return p;
        }

        return null;
    }

    public boolean passwordCorrect(Patient patient, String password) { // TODO think of some encryption system
        return patient.getPassword().equals(password);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            // TODO fix some jdialog headers
            signup = new JDialog(null, "Signup", JDialog.ModalityType.APPLICATION_MODAL);
            signup.add(createSignup());
            signup.setSize(new Dimension(350, 450));  // TODO bad size
            signup.setLocationRelativeTo(null);
            signup.setResizable(false);
            signup.setVisible(true);
        } else if (e.getSource() == login) {
            String inputEmail = emailInput.getText();
            String inputPassword = String.valueOf(passwordInput.getPassword());  // .getText() deprecated for JPasswordField
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

            // Clearning text so it won't appear when logged out
            emailInput.setText("");
            passwordInput.setText("");
        } else if (e.getSource() == registerConfirm) {
            // TODO if valid patient
            if (PopupHelper.validPatient(inputs, "")) {
                Patient registerPatient = createPatient();
                App.dsm.getPatientList().add(registerPatient);
                App.dsm.setCurrentUser(registerPatient);
                signup.setVisible(false);
                App.shmis.LoggedIn();
            } else {
                JOptionPane.showMessageDialog(null, PopupHelper.getError(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
