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
import com.company.utilities.FontColor;

/**
 * Page for employee login.
 */
public class EmployeeLogin extends JPanel implements ActionListener {
    private JLabel key;
    private JLabel password;

    private JTextField keyInput;
    private JPasswordField passwordInput;

    private JButton login;

    /**
     * Initializes the employee login page.
     */
    public EmployeeLogin() {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        key = new JLabel("Enter key:");
        password = new JLabel("Enter password:");

        keyInput = new JTextField();
        passwordInput = new JPasswordField();

        login = new JButton("Login");
        login.addActionListener(this);

        // Setting sizes and styling
        key.setFont(FontColor.H2_BOLD);
        key.setPreferredSize(new Dimension(120, 30));

        password.setFont(FontColor.H2_BOLD);
        password.setPreferredSize(new Dimension(120, 30));

        keyInput.setFont(FontColor.H2);
        keyInput.setPreferredSize(new Dimension(120, 30));

        passwordInput.setFont(FontColor.H2);
        passwordInput.setPreferredSize(new Dimension(120, 30));

        login.setFont(FontColor.H2);
        login.setPreferredSize(new Dimension(120+120+10, 30));

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

    /**
     * Performing actions when clicked on the login button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String inputKey = keyInput.getText();
        String inputPassword = String.valueOf(passwordInput.getPassword());  // .getText() deprecated for JPasswordField

        if (!inputKey.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Invalid employee key", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!inputPassword.equals("password")) {
            JOptionPane.showMessageDialog(null, "Incorrect password", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        App.dsm.setCurrentUser(new Employee());
        App.shmis.LoggedIn();

        // Clearning text so it won't appear when logged out
        keyInput.setText("");
        passwordInput.setText("");
    };
}
