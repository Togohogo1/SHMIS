package com.company.pages.program.tablemodels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.company.classes.Patient;

/**
 * Customized table model for displaying patients.
 */
public class PatientTableModel extends AbstractTableModel {
    private String[] columnNames = {"Age", "First Name", "Last Name", "Gender", "Id", "Password", "Address", "Email", "Telephone"};
    private ArrayList<Patient> patients;

    public PatientTableModel(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public int getRowCount() {
        return patients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;  // Don't want the last column (appointment indicies)
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient patient = patients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return patient.getAge();
            case 1:
                return patient.getFirstName();
            case 2:
                return patient.getLastName();
            case 3:
                return patient.getGender();
            case 4:
                return patient.getId();
            case 5:
                return "********";  // Better alternative to creating a custom renderer
            case 6:
                return patient.getAddress();
            case 7:
                return patient.getEmail();
            case 8:
                return patient.getTelephone();
        }

        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
