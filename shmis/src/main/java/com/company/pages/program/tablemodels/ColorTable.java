package com.company.pages.program.tablemodels;

import java.awt.Component;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.company.pages.FontColor;

/**
 * Custom table to display colours. Different for the patient queue, weekly calendar, and appointment list.
 */
public class ColorTable extends JTable {
    private String tableModelType;

    public ColorTable(TableModel tableModel, String tableModelType) {
        this.tableModelType = tableModelType;
        setModel(tableModel);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        boolean isQueue = (tableModelType == "queue");
        boolean isCalendar = (tableModelType == "calendar");
        String status = (String) getModel().getValueAt(row, 4);
        // System.out.println("render");
        if (isCalendar) {
            status = ((CalendarTableModel)getModel()).getStatusFromEvents(row);
            System.out.println(status + " " + row + " " + column);
        }

        if (tableModelType.equals("queue")) {
            if (!isRowSelected(row)) {
                c.setBackground(getBackground());
                c.setForeground(getForeground());
            }

            if (row != 0 && isQueue)  // As if its deselected for queue
                c.setForeground(Color.LIGHT_GRAY);
        } else {
            if (tableModelType.equals("calendar"))
                status = ((CalendarTableModel)getModel()).getStatusFromEvents(row);

            if (!isRowSelected(row) && tableModelType.equals("appointment") || tableModelType.equals("calendar")) {
                if (status.equals("Pending")) {
                    c.setBackground(FontColor.LIGHT_YELLOW_3);
                } else if (status.equals("Approved"))
                    c.setBackground(FontColor.LIGHT_GREEN_3);
                else if (status.equals("Unapproved"))
                    c.setBackground(FontColor.LIGHT_RED_3);
            }
        }

        return c;
    }
}
