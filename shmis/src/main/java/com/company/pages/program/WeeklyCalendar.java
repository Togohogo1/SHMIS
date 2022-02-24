package com.company.pages.program;

import java.util.ArrayList;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
import com.company.pages.program.tablemodels.CalendarTableModel;
import com.company.pages.program.tablemodels.ColorTable;
import com.company.utilities.SearchSort;

/**
 * Page for the weekly calendar
 */
public class WeeklyCalendar extends JPanel implements MouseListener, ListSelectionListener {
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
            tableModels[i] = new CalendarTableModel(names[i], days.get(i));  // Pass by reference, only need to do this once
            calendars[i] = new ColorTable(tableModels[i], "calendar"); // TODO change to colortable later
            calendars[i].addMouseListener(this);
            calendars[i].setCellSelectionEnabled(false);
            calendars[i].getSelectionModel().addListSelectionListener(this);
            calendarTables[i] = new JScrollPane(calendars[i]);
        }

        render();

        // Setting sizes and styling
        for (int i = 0; i < 5; i++) {
            calendars[i].setRowHeight(25);
            calendars[i].setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            calendars[i].getTableHeader().setFont(FontColor.H3_BOLD);
            calendars[i].getTableHeader().setPreferredSize(new Dimension(0, 30));  // Will auto resize
            calendars[i].getTableHeader().setReorderingAllowed(false);
        }
        // TODO sizes depending on time
        // Positioning
        for (int i = 0; i < 5; i++) {
            ci.gridx = i;
            ci.weighty = 1;
            ci.weightx = 0.2;
            ci.fill = GridBagConstraints.BOTH;
            panel.add(calendarTables[i], ci);
        }

        co.insets = new Insets(10, 10, 10, 10);
        co.weightx = 1;
        co.weighty = 1;
        co.fill = GridBagConstraints.BOTH;
        this.add(panel, co);
    }

    // Efficient enough for maximum calendar capacity, less prone to bugs
    public void render() {
        for (int i = 0; i < 5; i++) {
            if (!days.get(i).isEmpty())
                days.get(i).clear();
        }

        for (long idx : App.dsm.getInCalendar()) {
            Appointment appointment = App.dsm.query(idx);
            int day = dayToInt(appointment.getDate());  // Get day of each appointment in calendar list
            days.get(day).add(appointment);  // Unsorted, but distributed appointments from calendar list
        }

        // Ordering by start time
        for (ArrayList<Appointment> arr : days) {
            SearchSort.mergeSort(arr, appointment -> appointment.getStart());
        }

        for (CalendarTableModel model : tableModels) {
            model.fireTableDataChanged();
        }
    }

    public int dayToInt(String day) {
        switch (day) {  // TODO think about using array indicies instead
            case "Monday":
                return 0;
            case "Tuesday":
                return 1;
            case "Wednesday":
                return 2;
            case "Thursday":
                return 3;
            case "Friday":
                return 4;
        }

        return -1;
    }
    // TODO types of selection
    @Override
    public void mouseClicked(MouseEvent e) {
        if (App.dsm.currUserIsPatient())
            return;

        JTable target = (JTable) e.getSource();
        int row = target.getSelectedRow(); // select a row
        int day = dayToInt(((CalendarTableModel)target.getModel()).getDay());
        Appointment appointment = days.get(day).get(row);

        if (e.getClickCount() == 2) {
            if (appointment.getStatus().equals("Approved")) {
                int n = JOptionPane.showConfirmDialog(null, String.format("Mark %s's appointment (ID = %d) as complete?", appointment.getPatient(), appointment.getId()), "Complete Appointment", JOptionPane.YES_NO_OPTION);

                if (JOptionPane.YES_OPTION == n) {
                    appointment.setStatus("Completed");
                    App.dsm.getInCalendar().remove(appointment.getId());
                    render();
                }
            } else {
                JOptionPane.showMessageDialog(null, String.format("%s's appointment (ID = %d)", appointment.getPatient(), appointment.getId()), "Event Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
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