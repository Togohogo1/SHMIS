package com.company.pages.program.tablemodels;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;
import com.company.utilities.Queue;

/**
 * Customized table model for displaying queued appointments.
 */
public class QueueTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Patient", "Date", "From", "To"};
    private Queue queue;

    /**
     * Initializes a queue table model with a queue of appointments.
     *
     * @param queue The queue of appointments
     */
    public QueueTableModel(Queue queue) {
        this.queue = queue;
    }

    /**
     * Returns the row count.
     */
    @Override
    public int getRowCount() {
        return queue.getSize();
    }

    /**
     * Returns the column count.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the value at a specified row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        long appId = queue.get(rowIndex).getAppointmentID();
        Appointment appointment = App.dsm.query(appId);  // Bsearch appointment

        switch (columnIndex) {
            case 0:
                return appointment.getId();
            case 1:
                return appointment.getPatient();
            case 2:
                return appointment.getDate();
            case 3:
                return appointment.getStartTable();
            case 4:
                return appointment.getEndTable();
        }

        return null;
    }

    /**
     * Returns the name of a column.
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * Makes a cell uneditable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
