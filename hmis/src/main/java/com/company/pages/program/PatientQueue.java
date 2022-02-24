package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.company.App;
import com.company.classes.Appointment;
import com.company.pages.FontColor;
import com.company.pages.program.tablemodels.ColorTable;
import com.company.pages.program.tablemodels.QueueTableModel;

/**
 * Page for the patient queue.
 */
public class PatientQueue extends JPanel implements ActionListener, MouseListener, ListSelectionListener {
    private JTable table;
    private JScrollPane queueTable;
    private QueueTableModel tableModel;

    /**
     * Initializes the patient queue.
     */
    public PatientQueue() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        tableModel = new QueueTableModel(App.dsm.getQueue());
        table = new ColorTable(tableModel, "queue");
        table.addMouseListener(this);
        table.getSelectionModel().addListSelectionListener(this);
        queueTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Setting sizes and styling
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        table.getTableHeader().setFont(FontColor.H3_BOLD);
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));  // Will auto resize
        table.getTableHeader().setReorderingAllowed(false);

        // Positioning
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        this.add(queueTable, c);
    }

    /**
     * Returns the table model.
     *
     * @return the table model
     */
    public QueueTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Don't allow the employee to select appointments that are not the first (priority)
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();

        if (lsm.getMinSelectionIndex() >= 1)
            lsm.clearSelection();
    }

    /**
     * Displays a popup to approve or disapprove an appointment in the queue after double clicking on it.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();

        if (target.getSelectedRow() == 0) {  // Only allow the first row to be selected
            if (e.getClickCount() == 2) {
                int n = JOptionPane.showConfirmDialog(null, "Approve the first appointment in queue?", "Approve Appointment", JOptionPane.YES_NO_OPTION);
                long appId = App.dsm.getQueue().get(0).getAppointmentID();  // TODO new method?
                Appointment appointment = App.dsm.query(appId);

                if (JOptionPane.YES_OPTION == n) {
                    App.dsm.getQueue().removeFront();
                    appointment.setStatus("Approved");
                    tableModel.fireTableDataChanged();
                } else if (JOptionPane.NO_OPTION == n) {
                    App.dsm.getQueue().removeFront();
                    App.dsm.getInCalendar().remove(appId);
                    appointment.setStatus("Unapproved");
                }
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

    @Override
    public void actionPerformed(ActionEvent e) {}
}
