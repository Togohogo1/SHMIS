package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.CipherSpi;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Appointment;
import com.company.pages.program.tablemodels.AppointmentTableModel;

public class MyAppointments extends JPanel implements ActionListener {
    private JButton book;
    private JTable table;
    private JScrollPane myAppointments;

    // For booking popup
    private JDialog bookingPopup;
    private JRadioButton[] imaging;
    private JComboBox<String> day;
    private JComboBox<String> from;
    private JComboBox<String> to;
    private JComboBox<String> referralDoctor;
    private JTextField notes;
    private JButton bookConfirm;

    public MyAppointments() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        book = new JButton("Book Appointment");
        book.addActionListener(this);

        AppointmentTableModel tableModel = new AppointmentTableModel();
        tableModel.setAppointmentList(App.dsm.getCurrentUser().getAppointments());
        table = new JTable(tableModel);
        myAppointments = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Setting sizes and styling

        // Positioning
        c.gridy = 0;
        this.add(book, c);

        c.gridy = 1;
        this.add(myAppointments, c);
    }

    public JPanel createBooking() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints co = new GridBagConstraints();

        JPanel top = new JPanel(new GridBagLayout());
        GridBagConstraints ci = new GridBagConstraints();

        // Initilizing the elements
        imaging = new JRadioButton[7];
        String[] imagingText = {"Abdomen", "Spine and Pelvis", "Chest", "Upper Extremeties", "Head and Neck", "Lower Extremeties", "Skeletal"};

        for (int i = 0; i < 7; i++) {
            imaging[i] = new JRadioButton(imagingText[i]);
        }

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        day = new JComboBox<>(days);

        String[] starts = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"};
        from = new JComboBox<>(starts);

        String[] ends = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"};
        to = new JComboBox<>(ends);

        String[] doctors = {"Dr. Adams", "Dr. Brian", "Dr. Campbell", "Dr. Duncan", "Dr. Eaton"};
        referralDoctor = new JComboBox<>(doctors);

        notes = new JTextField();
        bookConfirm = new JButton("Book Appointment");
        // Setting sizes and styling

        // Positioning
        ci.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 7; i++) {
            ci.gridy = i;
            top.add(imaging[i], ci);
        }

        ci.gridx = 1;
        ci.gridy = 0;
        top.add(day, ci);

        ci.gridx = 1;
        ci.gridy = 1;
        top.add(from, ci);

        ci.gridx = 1;
        ci.gridy = 2;
        top.add(to, ci);

        ci.gridx = 1;
        ci.gridy = 3;
        top.add(referralDoctor, ci);

        ci.gridx = 1;
        ci.gridy = 4;
        top.add(notes, ci);

        co.gridy = 0;
        panel.add(top);

        co.gridy = 1;
        panel.add(bookConfirm, co);

        return panel;
    }

    public Appointment createAppointment() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog book = new JDialog(null, "Signup", JDialog.ModalityType.APPLICATION_MODAL);
        book.add(createBooking());
        book.setSize(new Dimension(500, 500));  // TODO bad size
        book.setLocationRelativeTo(null);
        book.setResizable(false);
        book.setVisible(true);
    }
}
