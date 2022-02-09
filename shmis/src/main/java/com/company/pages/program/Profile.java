package com.company.pages.program;

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
import com.company.classes.Employee;
import com.company.classes.Patient;
import com.company.classes.Person;

public class Profile extends JPanel implements ActionListener {
    private JButton logout;

    public Profile() {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        Person person = App.dsm.getCurrentUser();


        // Initializting the elements
        logout = new JButton("Logout");
        logout.addActionListener(this);

        JLabel firstName = new JLabel(person.getFirstName());
        JLabel lastName = new JLabel(person.getLastName());
        JLabel id = new JLabel(person.getId());
        JLabel email;
        JLabel age = new JLabel("" + person.getAge());
        JLabel birthdate;
        JLabel gender = new JLabel(person.getGender());
        JLabel address;
        JLabel telephone;

        if (person.getDesignation().equals("Patient")) {
            email = new JLabel(((Patient)person).getEmail());
            birthdate = new JLabel(((Patient)person).getBirthdate());
            address = new JLabel(((Patient)person).getAddress());
            telephone = new JLabel(((Patient)person).getTelephone());
        }
        // Setting sizes and styling

        // Positioning

        this.add(logout, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(null, "Confirm Logout?", "Option", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION)
            App.shmis.LogOut();
    }
}
