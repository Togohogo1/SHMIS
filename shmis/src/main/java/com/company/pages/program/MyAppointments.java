package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.company.App;
import com.company.pages.program.tablemodels.AppointmentTableModel;

public class MyAppointments extends JPanel implements ActionListener {
    private JButton book;
    private JTable table;
    private JScrollPane myAppointments;

    public MyAppointments() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        book = new JButton("Book Appointment");
        book.addActionListener(this);

        AppointmentTableModel tableModel = new AppointmentTableModel(App.dsm.getCurrentUser().getAppointments());
        table = new JTable(tableModel);
        myAppointments = new JScrollPane(myAppointments, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Setting sizes and styling

        // Positioning
        c.gridy = 0;
        this.add(book);

        c.gridy = 1;
        this.add(myAppointments);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog book = new JDialog(null, "Signup", JDialog.ModalityType.APPLICATION_MODAL);
        book.add(new Booking(book));
        book.setSize(new Dimension(500, 500));  // TODO bad size
        book.setLocationRelativeTo(null);
        book.setResizable(false);
        book.setVisible(true);
    }
}
