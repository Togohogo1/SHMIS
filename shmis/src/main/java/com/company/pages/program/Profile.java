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

        boolean isPatient = person.getDesignation().equals("Patient");
        JLabel[] patientlabels = new JLabel[9];
        JLabel[] employeeLabels = new JLabel[5];
        JLabel[] patientDetails = new JLabel[9];
        JLabel[] employeeDetails = new JLabel[5];

        patientlabels[0] = employeeLabels[0] = new JLabel("First Name:");
        patientlabels[1] = employeeLabels[1] = new JLabel("Last Name:");
        patientlabels[2] = employeeLabels[2] = new JLabel("ID:");
        patientlabels[3] = new JLabel("Email:");
        patientlabels[4] = employeeLabels[3] = new JLabel("Age:");
        patientlabels[5] = new JLabel("Birthdate:");
        patientlabels[6] = employeeLabels[4] = new JLabel("Gender:");
        patientlabels[7] = new JLabel("Address:");
        patientlabels[8] = new JLabel("Telephone:");

        patientDetails[0] = employeeDetails[0] = new JLabel(person.getFirstName());
        patientDetails[1] = employeeDetails[1] = new JLabel(person.getLastName());
        patientDetails[2] = employeeDetails[2] = new JLabel(person.getId());
        patientDetails[3] = new JLabel();
        patientDetails[4] = employeeDetails[3] = new JLabel("" + person.getAge());
        patientDetails[5] = new JLabel();
        patientDetails[6] = employeeDetails[4] = new JLabel(person.getGender());
        patientDetails[7] = new JLabel();
        patientDetails[8] = new JLabel();

        if (isPatient) {
            patientDetails[3] = new JLabel(((Patient)person).getEmail());
            patientDetails[5] = new JLabel(((Patient)person).getBirthdate());
            patientDetails[7] = new JLabel(((Patient)person).getAddress());
            patientDetails[8] = new JLabel(((Patient)person).getTelephone());
        }

        // Setting sizes and styling

        // Positioning
        c.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 2; i++) {  // TODO take advantage of more ternary statements?
            for (int j = 0; j < (isPatient ? 9 : 5); j++) {
                c.gridx = i;
                c.gridy = j;

                if (i == 0)
                    this.add((isPatient ? patientlabels[j] : employeeLabels[j]), c);
                else
                    this.add((isPatient ? patientDetails[j] : employeeDetails[j]), c);
            }
        }

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        this.add(logout, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(null, "Confirm Logout?", "Option", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION)
            App.shmis.LogOut();
    }
}
