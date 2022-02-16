package com.company.pages.program;

import java.util.ArrayList;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.company.App;
import com.company.classes.Appointment;
import com.company.pages.program.tablemodels.CalendarTableModel;

public class WeeklyCalendar extends JPanel {
    private String[] names = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private ArrayList<ArrayList<Appointment>> days = new ArrayList<>();
    private CalendarTableModel[] tableModels = new CalendarTableModel[5];
    private JTable[] calendars = new JTable[5];
    private JScrollPane[] calendarTables = new JScrollPane[5];


    public WeeklyCalendar() {
        super(new GridBagLayout());
        GridBagConstraints co = new GridBagConstraints();

        // Initializing the elements
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints ci = new GridBagConstraints();


        for (int i = 0; i < 5; i++) {
            days.add(new ArrayList<>());
            tableModels[i] = new CalendarTableModel(names[i], days.get(i));
            calendars[i] = new JTable(tableModels[i]);
            calendarTables[i] = new JScrollPane(calendars[i]);
        }

        // Setting sizes and styling

        // Positioning
        for (int i = 0; i < 5; i++) {
            ci.gridx = i;
            ci.weighty = 1;
            ci.weightx = 0.2;
            ci.fill = GridBagConstraints.BOTH;
            panel.add(calendarTables[i], ci);
        }


        co.insets = new Insets(5, 5, 5, 5);
        co.weightx = 1;
        co.weighty = 1;
        co.fill = GridBagConstraints.BOTH;
        this.add(panel, co);
    }
}
