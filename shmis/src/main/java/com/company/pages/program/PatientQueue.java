package com.company.pages.program;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import com.company.App;
import com.company.pages.program.tablemodels.QueueTableModel;

public class PatientQueue extends JPanel {
    private JTable table;
    private JScrollPane queueTable;

    public PatientQueue() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Initializing the elements
        QueueTableModel tableModel = new QueueTableModel(App.dsm.getQueue());
        table = new JTable(tableModel);
        queueTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Setting sizes and styling

        // Positioning
        this.add(queueTable, c);
    }
}
