package com.company.pages.program.tablemodels;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.company.utilities.FontColor;

/**
 * Custom table to display colors. Different for the patient queue, weekly calendar, and appointment list.
 */
public class ColorTable extends JTable {
    private String tableModelType;

    /**
     * Initializes a <code>JTable</code> that allows colors to be displayed.
     *
     * @param tableModel The table model of the table
     * @param tableModelType The type of table
     */
    public ColorTable(TableModel tableModel, String tableModelType) {
        this.tableModelType = tableModelType;
        setModel(tableModel);
    }

    /**
     * Returns a <code>JTable</code> component that renders the table with color.
     */
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        String status = (String) getModel().getValueAt(row, 4);  // Status in the 4th column
        boolean isQueue = (tableModelType.equals("queue"));
        boolean isCalendar = (tableModelType.equals("calendar"));  // Redundant but kept for consistency

        if (isCalendar)
            status = ((CalendarTableModel)getModel()).getStatusFromEvents(row);

        if (tableModelType.equals("queue")) {
            if (!isRowSelected(row)) {
                c.setBackground(getBackground());
                c.setForeground(getForeground());
            }

            // As if its deselected for queue
            if (row != 0 && isQueue)
                c.setForeground(Color.LIGHT_GRAY);

        } else {
            if (tableModelType.equals("calendar"))
                status = ((CalendarTableModel)getModel()).getStatusFromEvents(row);

            // Same colour scheme for my appointments and calendar pages
            if (!isRowSelected(row) && tableModelType.equals("appointment") || tableModelType.equals("calendar")) {
                if (status.equals("Pending"))
                    c.setBackground(FontColor.LIGHT_YELLOW_3);
                else if (status.equals("Approved"))
                    c.setBackground(FontColor.LIGHT_GREEN_3);
                else if (status.equals("Unapproved"))
                    c.setBackground(FontColor.LIGHT_RED_3);
                else
                    c.setBackground(getBackground());
            }
        }

        return c;
    }
}
