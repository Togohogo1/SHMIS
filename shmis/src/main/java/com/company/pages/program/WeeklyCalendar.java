package com.company.pages.program;

import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.company.App;
import com.company.classes.Appointment;
import com.company.pages.program.tablemodels.CalendarTableModel;
import com.company.pages.program.tablemodels.ColorTable;
import com.company.utilities.FontColor;
import com.company.utilities.SearchSort;

/**
 * Page for the weekly calendar.
 */
public class WeeklyCalendar extends JPanel implements MouseListener, ListSelectionListener {
    private ArrayList<ArrayList<Appointment>> days = new ArrayList<>();
    private CalendarTableModel[] tableModels = new CalendarTableModel[5];
    private JTable[] calendars = new JTable[5];
    private JScrollPane[] calendarTables = new JScrollPane[5];

    /**
     * Initializing the weekly calendar page.
     */
    public WeeklyCalendar() {
        super(new GridBagLayout());
        GridBagConstraints co = new GridBagConstraints();

        // Initializing the elements
        String[] names = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints ci = new GridBagConstraints();

        for (int i = 0; i < 5; i++) {
            days.add(new ArrayList<>());
            tableModels[i] = new CalendarTableModel(names[i], days.get(i));  // Pass by reference, only need to do this once
            calendars[i] = new ColorTable(tableModels[i], "calendar");
            calendars[i].addMouseListener(this);
            calendars[i].setCellSelectionEnabled(false); // Way to get around the deselect coloring issue
            calendars[i].getSelectionModel().addListSelectionListener(this);
            calendarTables[i] = new JScrollPane(calendars[i]);
        }

        render();

        // Setting sizes and styling
        for (int i = 0; i < 5; i++) {
            calendars[i].setRowHeight(25);
            calendars[i].setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            calendars[i].getTableHeader().setFont(FontColor.H3_BOLD);
            calendars[i].getTableHeader().setPreferredSize(new Dimension(0, 30)); // Will auto resize
            calendars[i].getTableHeader().setReorderingAllowed(false);
        }

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

    /**
     * Refreshes the calendar. Efficient enough for maximum calendar capacity, less
     * prone to bugs
     */
    public void render() {
        for (int i = 0; i < 5; i++) {
            if (!days.get(i).isEmpty())
                days.get(i).clear();
        }

        for (long idx : App.dsm.getInCalendar()) {
            Appointment appointment = App.dsm.query(idx);
            int day = dayToInt(appointment.getDate()); // Get day of each appointment in calendar list
            days.get(day).add(appointment); // Unsorted, but distributed appointments from calendar list
        }

        // Ordering by start time
        for (ArrayList<Appointment> arr : days) {
            SearchSort.mergeSort(arr, appointment -> appointment.getStart());
        }

        for (CalendarTableModel model : tableModels) {
            model.fireTableDataChanged();
        }
    }

    /**
     * Returns a value that corresponds with the day.
     *
     * @param day The day of the week
     * @return A value that corresponds with the day
     */
    public int dayToInt(String day) {
        if (day.equals("Monday"))
            return 0;
        else if (day.equals("Tuesday"))
            return 1;
        else if (day.equals("Wednesday"))
            return 2;
        else if (day.equals("Thursday"))
            return 3;
        else if (day.equals("Friday"))
            return 4;

        // Won't be reached unless error
        return -1;
    }

    /**
     * Only allow employees to mark calendar events as complete when double
     * clicking.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Don't let patients interact with the calendar
        if (App.dsm.currUserIsPatient())
            return;

        JTable target = (JTable) e.getSource();
        int row = target.getSelectedRow(); // Select a row
        int day = dayToInt(((CalendarTableModel)target.getModel()).getDay());
        Appointment appointment = days.get(day).get(row);

        if (e.getClickCount() == 2) {
            if (appointment.getStatus().equals("Approved")) {
                int n = JOptionPane.showConfirmDialog(null, String.format("Mark %s's appointment (ID = %d) as complete?", appointment.getPatient(), appointment.getId()), "Complete Appointment", JOptionPane.YES_NO_OPTION);

                // Marking appointment as complete
                if (n == JOptionPane.YES_OPTION) {
                    appointment.setStatus("Completed");
                    App.dsm.getInCalendar().remove(appointment.getId());
                    render();
                }

            // Double clicking on a pending appointment
            } else {
                JOptionPane.showMessageDialog(null, String.format("%s's appointment (ID = %d)", appointment.getPatient(), appointment.getId()), "Event Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
