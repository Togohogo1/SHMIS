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

public class PatientIndex extends JPanel implements ListSelectionListener, ActionListener {
    private int selectedRow;

    private JComboBox<String> sortBy;
    private JTextField searchBar;

    private JButton sort;

    private JTable tablePatients;
    private JTable tableAppointments;
    private JScrollPane patientTable;
    private JScrollPane correspondingAppts;

    public PatientIndex() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JSplitPane splitPane = new JSplitPane();
        JLabel sortLabel = new JLabel("Sort");
        JLabel searchLabel = new JLabel("Search");

        String[] sortOptions = {"Age", "First Name", "Last Name", "Address", "Email"};
        sortBy = new JComboBox<>(sortOptions);
        searchBar = new JTextField();
        sort = new JButton("Sort By");

        PatientTableModel patientTableModel = new PatientTableModel(App.dsm.getPatientList());
        AppointmentTableModel appointmentTableModel = new AppointmentTableModel();

        tablePatients = new JTable(patientTableModel);
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
        top.add(searchLabel);
        top.add(searchBar);

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
        // TODO Auto-generated method stub

    }
}
