package com.company.pages.program.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.company.App;
import com.company.classes.Appointment;

/**
 * Customized table model for displaying appointments.
 */
public class AppointmentTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Date", "From", "To", "Status"};
    private ArrayList<Long> appiontmentIDs;

    /**
     * Returns the row count.
     */
    @Override
    public int getRowCount() {
        return (appiontmentIDs == null ? 0 : appiontmentIDs.size());
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
        long appId = appiontmentIDs.get(rowIndex);
        Appointment appointment = App.dsm.query(appId);

        switch (columnIndex) {
            case 0:
                return appointment.getId()+"";
            case 1:
                return appointment.getDate();
            case 2:
                return appointment.getStartTable();
            case 3:
                return appointment.getEndTable();
            case 4:
                return appointment.getStatus();
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
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Sets the <code>ArrayList</code> that this table model uses to a new <code>ArrayList</code>.
     *
     * @param appointmentIDs The new <code>ArrayList</code>
     */
    public void setAppointmentList(ArrayList<Long> appointmentIDs) {
        this.appiontmentIDs = appointmentIDs;
    }

    /**
     * Returns the <code>ArrayList</code> that this table model uses.
     *
     * @return the <code>ArrayList</code> that this table model uses
     */
    public ArrayList<Long> getAppointmentList() {
        return appiontmentIDs;
    }
}
