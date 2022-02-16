package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
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
        GridBagConstraints co = new GridBagConstraints();

        Person person = App.dsm.getCurrentUser();

        // Initializting the elements
        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints ci = new GridBagConstraints();

        logout = new JButton("Logout");
        logout.addActionListener(this);

        boolean isPatient = person.getDesignation().equals("Patient");
        JLabel[] patientlabels = new JLabel[8];
        JLabel[] employeeLabels = new JLabel[5];
        JLabel[] patientDetails = new JLabel[8];
        JLabel[] employeeDetails = new JLabel[5];

        patientlabels[0] = employeeLabels[0] = new JLabel("Age:");
        patientlabels[1] = employeeLabels[1] = new JLabel("First Name:");
        patientlabels[2] = employeeLabels[2] = new JLabel("Last Name:");
        patientlabels[3] = employeeLabels[3] = new JLabel("Gender:");
        patientlabels[4] = employeeLabels[4] = new JLabel("ID:");
        patientlabels[5] = new JLabel("Address:");
        patientlabels[6] = new JLabel("Email:");
        patientlabels[7] = new JLabel("Telephone:");

        patientDetails[0] = employeeDetails[0] = new JLabel("" + person.getAge());
        patientDetails[1] = employeeDetails[1] = new JLabel(person.getFirstName());
        patientDetails[2] = employeeDetails[2] = new JLabel(person.getLastName());
        patientDetails[3] = employeeDetails[3] = new JLabel(person.getGender());
        patientDetails[4] = employeeDetails[4] = new JLabel(person.getId());
        patientDetails[5] = new JLabel();
        patientDetails[6] = new JLabel();
        patientDetails[7] = new JLabel();

        if (isPatient) {
            patientDetails[5] = new JLabel(((Patient)person).getAddress());
            patientDetails[6] = new JLabel(((Patient)person).getEmail());
            patientDetails[7] = new JLabel(((Patient)person).getTelephone());
        }

        // Setting sizes and styling

        // Positioning
        ci.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 2; i++) {  // TODO take advantage of more ternary statements?
            for (int j = 0; j < (isPatient ? 8 : 5); j++) {
                ci.gridx = i;
                ci.gridy = j;

                if (i == 0)
                    top.add((isPatient ? patientlabels[j] : employeeLabels[j]), ci);
                else
                    top.add((isPatient ? patientDetails[j] : employeeDetails[j]), ci);
            }
        }

        top.setBackground(Color.CYAN);        // DEBUG

        co.gridy = 0;
        this.add(top, co);

        co.gridy = 1;
        this.add(logout, co);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(null, "Confirm Logout?", "Option", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION)
            App.shmis.LogOut();
    }
}
