package com.company.pages.program;

import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.net.SocketTimeoutException;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Patient;
import com.company.pages.program.tablemodels.AppointmentTableModel;
import com.company.pages.program.tablemodels.PatientTableModel;

public class PatientIndex extends JPanel implements ListSelectionListener, ActionListener, MouseListener {
    private int selectedRow;
    private String prevEmail;

    private JComboBox<String> sortBy;
    private JButton sort;

    private JTable tablePatients;
    private JTable tableAppointments;
    private PatientTableModel patientTableModel;
    private AppointmentTableModel appointmentTableModel;
    private JScrollPane patientTable;
    private JScrollPane correspondingAppts;

    // For patient popup
    private JDialog editPopup;
    private JTextField[] inputs;
    private JButton confirm;
    private JButton delete;


    // For appointment popup

    public PatientIndex() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JSplitPane splitPane = new JSplitPane();
        JLabel sortLabel = new JLabel("Sort By");

        String[] sortOptions = {"Age", "First Name", "Last Name", "Address", "Email"};
        sortBy = new JComboBox<>(sortOptions);
        sort = new JButton("Sort");

        patientTableModel = new PatientTableModel(App.dsm.getPatientList());
        appointmentTableModel = new AppointmentTableModel();

        tablePatients = new JTable(patientTableModel);
        tablePatients.addMouseListener(this);
        tablePatients.getSelectionModel().addListSelectionListener(this);

        tableAppointments = new JTable(appointmentTableModel);
        tablePatients.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableAppointments.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        patientTable = new JScrollPane(tablePatients, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        correspondingAppts = new JScrollPane(tableAppointments, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, patientTable, correspondingAppts);
        splitPane.setDividerLocation(150);  // Weird dividing behaviour (maybe its just a consequence of gridbaglayout)
        // Setting sizes and styling

        // Positioning
        top.add(sortLabel);
        top.add(sortBy);
        top.add(sort);
        // TODO add filler here?

        c.gridy = 0;
        c.insets = new Insets(0, 5, 5, 5);
        this.add(top, c);

        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        // splitPane.setPreferredSize(new Dimension(400, 400));
        this.add(splitPane, c);
    }

    public JPanel createEdit(Patient patient) {
        // Initialiing the elements
        JPanel popup = new JPanel(new GridBagLayout());  // To put the stuff in
        GridBagConstraints co = new GridBagConstraints();

        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints ci = new GridBagConstraints();
        inputs = new JTextField[9];

        confirm = new JButton("Confirm Edits");
        confirm.addActionListener(this);

        delete = new JButton("Delete Patient");
        delete.addActionListener(this);

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

        inputs[0].setText(Long.toString(patient.getAge()));
        inputs[1].setText(patient.getFirstName());
        inputs[2].setText(patient.getLastName());
        inputs[3].setText(patient.getGender());
        inputs[4].setText(patient.getId());
        inputs[5].setText(patient.getPassword());
        inputs[6].setText(patient.getAddress());
        inputs[7].setText(patient.getEmail());
        inputs[8].setText(patient.getTelephone());

        // Setting sizes and styling

        // Positioning
        ci.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                ci.gridx = i;
                ci.gridy = j;

                top.add((i == 0 ? labels[j] : inputs[j]), ci);
            }
        }

        co.gridx = 0;
        co.gridy = 0;
        co.gridwidth = 2;
        popup.add(top, co);

        co.gridx = 0;
        co.gridy = 1;
        co.gridwidth = 1;
        popup.add(confirm, co);

        co.gridx = 1;
        co.gridy = 1;
        popup.add(delete, co);

        return popup;
    }

    public void changePatient() {
        Patient patient = App.dsm.getPatientList().get(selectedRow);

        patient.setAge(Long.valueOf(inputs[0].getText()));
        patient.setFirstName(inputs[1].getText());
        patient.setLastName(inputs[2].getText());
        patient.setGender(inputs[3].getText());
        patient.setId(inputs[4].getText());
        patient.setPassword(inputs[5].getText());
        patient.setAddress(inputs[6].getText());
        patient.setEmail(inputs[7].getText());
        patient.setTelephone(inputs[8].getText());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        int firstIndex = lsm.getMinSelectionIndex();
        int lastIndex = lsm.getMaxSelectionIndex();

        if (firstIndex >= 0) {
            selectedRow = firstIndex;
            System.out.println("changes");  // DEBUG
            // TODO set the other table for changes
            // TODO fire data changes in the other table
        }
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {

            // Confirm that the new input is valid
            if (PopupHelper.validPatient(inputs, prevEmail)) {
                changePatient();
                patientTableModel.fireTableDataChanged();
                editPopup.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, PopupHelper.getError(), "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == delete) {
            int n = JOptionPane.showConfirmDialog(null, "Confirm deletion of patient?", "Delete Patient", JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_OPTION == n) {
                App.dsm.getPatientList().remove(selectedRow);
                patientTableModel.fireTableDataChanged();  // maybe change to another firexxx method
                editPopup.setVisible(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow(); // select a row
        int column = target.getSelectedColumn(); // select a column

        if (e.getClickCount() == 2) {
            Patient patient = App.dsm.getPatientList().get(row);
            prevEmail = patient.getEmail();
            editPopup = new JDialog(null, "Edit Patient", Dialog.ModalityType.APPLICATION_MODAL);
            editPopup.add(createEdit(patient));
            editPopup.setSize(new Dimension(250, 500));  // TODO make it a better size
            editPopup.setLocationRelativeTo(null);
            editPopup.setResizable(false);
            editPopup.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
