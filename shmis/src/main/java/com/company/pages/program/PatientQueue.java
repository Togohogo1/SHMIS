package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.company.App;
import com.company.classes.Appointment;
import com.company.pages.program.tablemodels.ColorTable;
import com.company.pages.program.tablemodels.QueueTableModel;
import com.company.utilities.SearchSort;

public class PatientQueue extends JPanel implements ActionListener, MouseListener, ListSelectionListener {
    private JTable table;
    private JScrollPane queueTable;
    private JDialog approvePopup;
    private QueueTableModel tableModel;

    public PatientQueue() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        tableModel = new QueueTableModel(App.dsm.getQueue());
        table = new ColorTable(tableModel, "queue");
        table.addMouseListener(this);
        table.getSelectionModel().addListSelectionListener(this);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        queueTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Setting sizes and styling

        // Positioning
        this.add(queueTable, c);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        System.out.println(lsm.getMinSelectionIndex());

        if (lsm.getMinSelectionIndex() >= 1)
            lsm.clearSelection();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        int row = target.getSelectedRow(); // select a row
        int column = target.getSelectedColumn(); // select a column

        if (target.getSelectedRow() == 0) {
            if (e.getClickCount() == 2) {
                int n = JOptionPane.showConfirmDialog(null, "Approve the first appointment in queue?", "Approve Appointment", JOptionPane.YES_NO_OPTION);
                long appId = App.dsm.getQueue().get(0).getAppointmentID();  // TODO new method?
                Appointment appointment = App.dsm.getAppointmentList().get(SearchSort.binarySearch(App.dsm.getAppointmentList(), appt -> appt.getId(), appId));
                App.dsm.getQueue().removeFront();

                if (JOptionPane.YES_OPTION == n) {
                    System.out.println("Approved");
                    appointment.setStatus("Approved");
                    // TODO and more db stuff
                    tableModel.fireTableDataChanged();
                } else {
                    appointment.setStatus("Unapproved");
                    System.out.println("Unapproved");
                    // TODO and more db stuff
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
