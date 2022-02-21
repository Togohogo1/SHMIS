package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Period;
import java.util.ArrayList;

import javax.crypto.CipherSpi;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Appointment;
import com.company.classes.Patient;
import com.company.classes.Person;
import com.company.pages.program.tablemodels.AppointmentTableModel;
import com.company.pages.program.tablemodels.ColorTable;
import com.company.utilities.Node;

public class MyAppointments extends JPanel implements ActionListener, MouseListener{
    private JButton book;
    private JTable table;
    private AppointmentTableModel appointmentTableModel;
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

    // For appointment info popup
    private JDialog appInfoPopup;
    private JRadioButton[] imagingView;
    private JComboBox<String> dayView;
    private JComboBox<String> fromView;
    private JComboBox<String> toView;
    private JComboBox<String> referralDoctorView;
    private JTextField notesView;

    public MyAppointments() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        book = new JButton("Book Appointment");
        book.addActionListener(this);

        appointmentTableModel = new AppointmentTableModel();
        appointmentTableModel.setAppointmentList(((Patient)App.dsm.getCurrentUser()).getAppointments());
        table = new ColorTable(appointmentTableModel, "appointment");
        table.addMouseListener(this);
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
        String[] imagingText = {"Abdomen", "Head and Neck", "Chest", "Skeletal", "Spine and Pelvis", "Upper Extremeties", "Lower Extremeties"};

        for (int i = 0; i < 7; i++) {
            imaging[i] = new JRadioButton(imagingText[i]);
        }

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        day = new JComboBox<>(days);

        String[] starts = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"};
        from = new JComboBox<>(starts);

        String[] ends = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"};
        to = new JComboBox<>(ends);

        String[] doctors = {"Dr. Adams", "Dr. Brian", "Dr. Campbell", "Dr. Duncan", "Dr. Eaton"};
        referralDoctor = new JComboBox<>(doctors);

        notes = new JTextField("Notes");
        bookConfirm = new JButton("Book Appointment");
        bookConfirm.addActionListener(this);
        // Setting sizes and styling

        // Positioning
        ci.insets = new Insets(5, 5, 5, 5);
        ci.gridx = 2;

        for (int i = 0; i < 7; i++) {
            ci.gridy = i;
            top.add(imaging[i], ci);
        }

        ci.gridy = 0;
        ci.gridx = 0;
        top.add(new JLabel("Day:"), ci);
        ci.gridx = 1;
        top.add(day, ci);

        ci.gridy = 1;
        ci.gridx = 0;
        top.add(new JLabel("From:"), ci);
        ci.gridx = 1;
        top.add(from, ci);

        ci.gridy = 2;
        ci.gridx = 0;
        top.add(new JLabel("To:"), ci);
        ci.gridx = 1;
        top.add(to, ci);

        ci.gridy = 3;
        ci.gridx = 0;
        top.add(new JLabel("Ref. Doctor:"), ci);
        ci.gridx = 1;
        top.add(referralDoctor, ci);

        ci.gridy = 4;
        ci.gridx = 0;
        ci.gridwidth = 2;
        top.add(notes, ci);

        co.gridy = 0;
        panel.add(top);

        co.gridy = 1;
        panel.add(bookConfirm, co);

        return panel;
    }

    public JPanel createApptInfo(Appointment appointment) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        imagingView = new JRadioButton[7];
        String[] imagingText = {"Abdomen", "Head and Neck", "Chest", "Skeletal", "Spine and Pelvis", "Upper Extremeties", "Lower Extremeties"};

        for (int i = 0; i < 7; i++) {
            imagingView[i] = new JRadioButton(imagingText[i]);
            imagingView[i].setSelected(appointment.getImaging().get(i));
            imagingView[i].setEnabled(false);
        }

        dayView = new JComboBox<>(new String[]{appointment.getDate()});
        dayView.setEnabled(false);

        fromView = new JComboBox<>(new String[]{appointment.getStartTable()});
        fromView.setEnabled(false);

        toView = new JComboBox<>(new String[]{appointment.getEndTable()});
        toView.setEnabled(false);

        referralDoctorView = new JComboBox<>(new String[]{appointment.getReferralDoctor()});
        referralDoctorView.setEnabled(false);

        notesView = new JTextField(appointment.getNotes());
        notesView.setEditable(false);

        // Setting sizes and styling


        // Positioning
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 2;

        for (int i = 0; i < 7; i++) {
            c.gridy = i;
            panel.add(imagingView[i], c);
        }

        c.gridy = 0;
        c.gridx = 0;
        panel.add(new JLabel("Day:"), c);
        c.gridx = 1;
        panel.add(dayView, c);

        c.gridy = 1;
        c.gridx = 0;
        panel.add(new JLabel("From:"), c);
        c.gridx = 1;
        panel.add(fromView, c);

        c.gridy = 2;
        c.gridx = 0;
        panel.add(new JLabel("To:"), c);
        c.gridx = 1;
        panel.add(toView, c);

        c.gridy = 3;
        c.gridx = 0;
        panel.add(new JLabel("Ref. Doctor:"), c);
        c.gridx = 1;
        panel.add(referralDoctorView, c);

        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 2;
        panel.add(notesView, c);

        return panel;
    }

    public Appointment createAppointment() {
        ArrayList<Boolean> imagingArr = new ArrayList<>();

        for (JRadioButton i : imaging) {
            imagingArr.add(i.isSelected());
        }

        Appointment appointment = new Appointment(
            PopupHelper.stringToStart((String)from.getSelectedItem()),
            PopupHelper.stringToStart((String)to.getSelectedItem()) - PopupHelper.stringToStart((String)from.getSelectedItem()),
            App.dsm.getAppointmentID(),
            (String) day.getSelectedItem(),
            "Pending",
            App.dsm.getCurrentUser().getFirstName() + " " + App.dsm.getCurrentUser().getLastName(),  // Full name
            (String) referralDoctor.getSelectedItem(),
            notes.getText(),
            imagingArr
        );

        App.dsm.incrAppointmentID();
        return appointment;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == book) {
            bookingPopup = new JDialog(null, "Signup", JDialog.ModalityType.APPLICATION_MODAL);
            bookingPopup.add(createBooking());
            bookingPopup.setSize(new Dimension(500, 500));  // TODO bad size
            bookingPopup.setLocationRelativeTo(null);
            bookingPopup.setResizable(false);
            bookingPopup.setVisible(true);
        } else if (e.getSource() == bookConfirm) {
            if (!PopupHelper.validAppointment(imaging, from, to))
                JOptionPane.showMessageDialog(null, PopupHelper.getError(), "Warning", JOptionPane.WARNING_MESSAGE);
            else {
                Appointment appt = createAppointment();
                if (PopupHelper.existsTimeConflict(appt))
                    JOptionPane.showMessageDialog(null, PopupHelper.getError(), "Warning", JOptionPane.WARNING_MESSAGE);
                else {
                    App.dsm.getAppointmentList().add(appt);
                    App.dsm.getQueue().insertFront(new Node(appt.getId()));
                    App.dsm.getInCalendar().add(appt.getId());
                    ((Patient)App.dsm.getCurrentUser()).addAppointment(appt.getId());
                    bookingPopup.setVisible(false);
                    appointmentTableModel.fireTableDataChanged();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        int row = target.getSelectedRow(); // select a row
        int column = target.getSelectedColumn(); // select a column

        if (e.getClickCount() == 2) {
            long appId = ((Patient)App.dsm.getCurrentUser()).getAppointments().get(row);
            Appointment appointment = App.dsm.query(appId);
            appInfoPopup = new JDialog(null, "Appointment Info", JDialog.ModalityType.APPLICATION_MODAL);
            appInfoPopup.add(createApptInfo(appointment));
            appInfoPopup.setSize(new Dimension(500, 500));  // TODO bad size
            appInfoPopup.setLocationRelativeTo(null);
            appInfoPopup.setResizable(false);
            appInfoPopup.setVisible(true);
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
}
