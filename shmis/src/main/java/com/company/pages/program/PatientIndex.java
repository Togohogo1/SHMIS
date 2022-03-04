package com.company.pages.program;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.company.App;
import com.company.classes.Appointment;
import com.company.classes.Patient;
import com.company.pages.program.tablemodels.AppointmentTableModel;
import com.company.pages.program.tablemodels.ColorTable;
import com.company.pages.program.tablemodels.PatientTableModel;
import com.company.utilities.FontColor;
import com.company.utilities.PopupHelper;
import com.company.utilities.SearchSort;

/**
 * Page for the patient index.
 */
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

    // For appointment info popup
    private JDialog appInfoPopup;
    private JRadioButton[] imagingView;
    private JComboBox<String> dayView;
    private JComboBox<String> fromView;
    private JComboBox<String> toView;
    private JComboBox<String> referralDoctorView;
    private JTextField notesView;

    /**
     * Initializes the patient index page.
     */
    public PatientIndex() {
        super(new GridBagLayout());
        GridBagConstraints co = new GridBagConstraints();

        // Initializing the elements
        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints ci = new GridBagConstraints();
        JSplitPane splitPane = new JSplitPane();

        String[] sortOptions = {"Age", "First Name", "Last Name", "Address", "Email"};
        sortBy = new JComboBox<>(sortOptions);
        sort = new JButton("Sort");
        sort.addActionListener(this);

        patientTableModel = new PatientTableModel(App.dsm.getPatientList());
        tablePatients = new JTable(patientTableModel);
        tablePatients.addMouseListener(this);
        tablePatients.getSelectionModel().addListSelectionListener(this);
        patientTable = new JScrollPane(tablePatients, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        appointmentTableModel = new AppointmentTableModel();
        tableAppointments = new ColorTable(appointmentTableModel, "appointment");
        tableAppointments.addMouseListener(this);
        correspondingAppts = new JScrollPane(tableAppointments, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, patientTable, correspondingAppts);
        splitPane.setDividerLocation(450);  // Weird dividing behaviour, could be consequence of GridBagLayout

        // Setting sizes and styling
        sortBy.setFont(FontColor.H2);
        sortBy.setPreferredSize(new Dimension(150, 30));
        sort.setFont(FontColor.H2);
        sort.setPreferredSize(new Dimension(120, 30));

        tablePatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePatients.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  // Allow horizontal scrolling
        tablePatients.setRowHeight(25);
        tablePatients.getTableHeader().setReorderingAllowed(false);
        tablePatients.getTableHeader().setPreferredSize(new Dimension(0, 30));
        tablePatients.getTableHeader().setFont(FontColor.H3_BOLD);;

        tableAppointments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAppointments.setRowHeight(25);
        tableAppointments.getTableHeader().setReorderingAllowed(false);
        tableAppointments.getTableHeader().setPreferredSize(new Dimension(0, 30));  // Will auto resize
        tableAppointments.getTableHeader().setFont(FontColor.H3_BOLD);;

        // Positioning
        ci.insets = new Insets(10, 5, 5, 5);
        top.add(sortBy, ci);
        top.add(sort, ci);

        co.gridy = 0;
        this.add(top, co);

        co.gridy = 1;
        co.weightx = 1;
        co.weighty = 1;
        co.fill = GridBagConstraints.BOTH;
        co.insets = new Insets(5, 10, 10, 10);
        this.add(splitPane, co);
    }

    /**
     * Creates a popup for employees to edit patient information.
     *
     * @param patient The patient whose information is edited.
     * @return a popup for employees to edit patient information
     */
    public JPanel createEdit(Patient patient) {
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
        confirm = new JButton("Confirm Edits");
        confirm.addActionListener(this);
        delete = new JButton("Delete Patient");
        delete.addActionListener(this);

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
        for (int i = 0; i < 9; i++) {
            labels[i].setPreferredSize(new Dimension(100, 22));  // Default JButton height
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
        c.insets = new Insets(15, 5, 5, 5);
        popup.add(confirm, c);

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.insets = new Insets(0, 5, 5, 5);
        popup.add(delete, c);

        return popup;
    }

    /**
     * Creates a popup to display patient appointment information.
     *
     * @param appointment The appointment of interest
     * @return a popup to display patient appointment information
     */
    public JPanel createApptInfo(Appointment appointment) {
        // Initializing the JPanel to be returned
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        JLabel dayLabel = new JLabel("Day:");
        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");
        JLabel referralDoctorLabel = new JLabel("Ref. Doctor:");
        imagingView = new JRadioButton[7];

        String[] imagingText = {"Abdomen", "Head and Neck", "Chest", "Skeletal", "Spine and Pelvis", "Upper Extremities", "Lower Extremities"};
        for (int i = 0; i < 7; i++) {
            imagingView[i] = new JRadioButton(imagingText[i]);
            imagingView[i].setSelected(appointment.getImaging().get(i));
            imagingView[i].setEnabled(false);
        }

        dayView = new JComboBox<>(new String[]{appointment.getDate()});
        dayView.setEnabled(false);

        fromView = new JComboBox<>(new String[]{appointment.getStartTable()});
        fromView.setEnabled(false);

        toView = new JComboBox<>(new String[]{appointment.getEndTable()});
        toView.setEnabled(false);

        referralDoctorView = new JComboBox<>(new String[]{appointment.getReferralDoctor()});
        referralDoctorView.setEnabled(false);

        notesView = new JTextField(appointment.getNotes());
        notesView.setEditable(false);

        // Setting sizes and styling
        dayLabel.setPreferredSize(new Dimension(75, 22));
        fromLabel.setPreferredSize(new Dimension(75, 22));
        toLabel.setPreferredSize(new Dimension(75, 22));
        referralDoctorLabel.setPreferredSize(new Dimension(75, 22));

        dayView.setPreferredSize(new Dimension(100, 22));
        fromView.setPreferredSize(new Dimension(100, 22));
        toView.setPreferredSize(new Dimension(100, 22));
        referralDoctorView.setPreferredSize(new Dimension(100, 22));
        notesView.setPreferredSize(new Dimension(100+75+10, 22));

        for (int i = 0; i < 7; i++) {
            imagingView[i].setPreferredSize(new Dimension(120, 22));
        }

        // Positioning
        c.gridx = 2;
        c.insets = new Insets(5, 15, 5, 5);

        for (int i = 0; i < 7; i++) {
            c.gridy = i;
            panel.add(imagingView[i], c);
        }

        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(dayLabel, c);
        c.gridx = 1;
        panel.add(dayView, c);

        c.gridy = 1;
        c.gridx = 0;
        panel.add(fromLabel, c);
        c.gridx = 1;
        panel.add(fromView, c);

        c.gridy = 2;
        c.gridx = 0;
        panel.add(toLabel, c);
        c.gridx = 1;
        panel.add(toView, c);

        c.gridy = 3;
        c.gridx = 0;
        panel.add(referralDoctorLabel, c);
        c.gridx = 1;
        panel.add(referralDoctorView, c);

        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 2;
        panel.add(notesView, c);

        return panel;
    }

    /**
     * Changes patient information based on edits in the patient edit popup. Assumes patient information is valid.
     *
     * @param patient The patient whose information is changed
     */
    public void changePatient(Patient patient) {
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

    /**
     * Change the patient's full name for all appointments that they are a reference to.
     *
     * @param patient The patient of interest
     */
    public void changeAppointmentRefs(Patient patient) {
        for (long appId : patient.getAppointments()) {
            Appointment appointment = App.dsm.query(appId);
            appointment.setPatient(patient.getFirstName() + " " + patient.getLastName());
        }
    }

    /**
     * Displaying corresponding appointments when selecting a patient.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        int firstIndex = lsm.getMinSelectionIndex();

        if (firstIndex >= 0) {
            selectedRow = firstIndex;
            appointmentTableModel.setAppointmentList(App.dsm.getPatientList().get(firstIndex).getAppointments());
            appointmentTableModel.fireTableDataChanged();
        }
    }

    /**
     * Performing actions when pressing the confirm, delete patient, and sort buttons.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Returning when no patients in the list to prevent indexing errors
        if (App.dsm.getPatientList().isEmpty())
            return;

        Patient patient = App.dsm.getPatientList().get(selectedRow);

        if (e.getSource() == confirm) {
            // Confirm that the new input is valid
            if (PopupHelper.validPatient(inputs, prevEmail)) {
                changePatient(patient);
                changeAppointmentRefs(patient);  // Also need to change appointments that reference the patient
                patientTableModel.fireTableDataChanged();
                editPopup.setVisible(false);
            } else
                JOptionPane.showMessageDialog(null, PopupHelper.getError(), "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (e.getSource() == delete) {
            int n = JOptionPane.showConfirmDialog(null, "Confirm deletion of patient?", "Delete Patient", JOptionPane.YES_NO_OPTION);

            // Confirm deletion
            if (n == JOptionPane.YES_OPTION) {
                // Removing patients from queue and calendar
                for (long i : patient.getAppointments()) {
                    App.dsm.getQueue().remove(i);
                    App.dsm.getInCalendar().remove(i);
                }

                App.dsm.getPatientList().remove(selectedRow);
                appointmentTableModel.getAppointmentList().clear();  // Patient's appointments still referenced by the table model
                appointmentTableModel.fireTableDataChanged();
                patientTableModel.fireTableDataChanged();
                editPopup.setVisible(false);
            }

        } else if (e.getSource() == sort) {
            String sortOption = (String) sortBy.getSelectedItem();

            if (sortOption.equals("Age"))
                SearchSort.mergeSort(App.dsm.getPatientList(), pat -> pat.getAge());
            else if (sortOption.equals("First Name"))
                SearchSort.mergeSort(App.dsm.getPatientList(), pat -> pat.getFirstName());
            else if (sortOption.equals("Last Name"))
                SearchSort.mergeSort(App.dsm.getPatientList(), pat -> pat.getLastName());
            else if (sortOption.equals("Address"))
                SearchSort.mergeSort(App.dsm.getPatientList(), pat -> pat.getAddress());
            else if (sortOption.equals("Email"))
                SearchSort.mergeSort(App.dsm.getPatientList(), pat -> pat.getEmail());

            patientTableModel.fireTableDataChanged();
        }
    }

    /**
     * Displaying popups when double clicking on a a patient or appointment.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow(); // select a row

        if (e.getClickCount() == 2) {
            if (target == tablePatients) {
                Patient patient = App.dsm.getPatientList().get(row);
                prevEmail = patient.getEmail();

                editPopup = new JDialog(null, "Edit Patient", Dialog.ModalityType.APPLICATION_MODAL);
                editPopup.add(createEdit(patient));
                editPopup.setSize(new Dimension(350, 450));
                editPopup.setLocationRelativeTo(null);
                editPopup.setResizable(false);
                editPopup.setVisible(true);

            } else if (target == tableAppointments) {
                long appId = appointmentTableModel.getAppointmentList().get(row);
                Appointment appointment = App.dsm.query(appId);

                appInfoPopup = new JDialog(null, "Appointment Information", JDialog.ModalityType.APPLICATION_MODAL);
                appInfoPopup.add(createApptInfo(appointment));
                appInfoPopup.setSize(new Dimension(450, 325));
                appInfoPopup.setLocationRelativeTo(null);
                appInfoPopup.setResizable(false);
                appInfoPopup.setVisible(true);
            }
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
