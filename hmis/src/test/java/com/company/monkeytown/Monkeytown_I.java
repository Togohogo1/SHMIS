package com.company.monkeytown;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import com.company.utilities.Node;
import com.company.utilities.Queue;

/*
===== table for queueing =====
[x] custom table model for own impl of queue
[x] only able to double click the first item in queue
    [x] remove first patient
[x] change row color (modify colours manually in code)
[x] add first item to queue
*/

public class Monkeytown_I extends JFrame implements WindowListener {
    public Monkeytown_I() {
        super("Monkeytown I");
        this.add(new Broski());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.setVisible(true);
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        FlatLightLaf.install();
        new Monkeytown_I();
    }


    /**
     * @param e
     */
    @Override
    public void windowOpened(WindowEvent e) {}


    /**
     * @param e
     */
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing");
    }


    /**
     * @param e
     */
    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Closed");
    }


    /**
     * @param e
     */
    @Override
    public void windowIconified(WindowEvent e) {}


    /**
     * @param e
     */
    @Override
    public void windowDeiconified(WindowEvent e) {}


    /**
     * @param e
     */
    @Override
    public void windowActivated(WindowEvent e) {}


    /**
     * @param e
     */
    @Override
    public void windowDeactivated(WindowEvent e) {}
}

class Broski extends JPanel implements ActionListener, MouseListener, ListSelectionListener {
    public static ArrayList<Banana> bananas = new ArrayList<>();

    private JButton add;
    private JTable bananaTable;
    private QueueTableModel bananaTableModel;
    private JScrollPane embed;
    private Queue _bananas;
    private JButton delete;
    private JDialog popup;

    public Broski() {
        delete = new JButton("delete the first?");
        delete.addActionListener(this);

        bananas.add(new Banana(1, "green"));
        bananas.add(new Banana(2, "sweet"));
        bananas.add(new Banana(3, "green"));
        bananas.add(new Banana(4, "brown"));
        bananas.add(new Banana(5, "green"));
        bananas.add(new Banana(6, "yellow"));

        add = new JButton("add banana");
        add.addActionListener(this);

        _bananas = new Queue();
        _bananas.insertFront(new Node(1));
        _bananas.insertFront(new Node(4));
        _bananas.insertFront(new Node(5));
        _bananas.insertFront(new Node(6));

        bananaTableModel = new QueueTableModel(_bananas);
        bananaTable = new ColorTable(bananaTableModel);
        bananaTable.getSelectionModel().addListSelectionListener(this);
        bananaTable.addMouseListener(this);
        // bananaTable.setFocusable(false);
        // bananaTable.setRowSelectionAllowed(false);


        bananaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bananaTable.setRowHeight(0, 50);

        embed = new JScrollPane(bananaTable);

        this.add(add);
        this.add(embed);
    }

    public JPanel createPopup() {
        JPanel popup = new JPanel();
        popup.add(delete);
        return popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == add) {
            _bananas.insertFront(new Node(3));
            // bananaTableModel.fireTableRowsInserted(firstRow, lastRow);
            bananaTableModel.fireTableDataChanged();
        } else if (e.getSource() == delete) {
            int n = JOptionPane.showConfirmDialog(null, "You really want to do this bro????", "Confirm Monkey Extermination", JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_OPTION == n) {
                _bananas.removeFront();
                bananaTableModel.fireTableDataChanged();
                popup.setVisible(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        JTable target = (JTable) e.getSource();
        int row = target.getSelectedRow(); // select a row
        int column = target.getSelectedColumn(); // select a column

        if (target.getSelectedRow() == 0) {
            if (e.getClickCount() == 2) {
                popup = new JDialog(null, "Monkeytown", Dialog.ModalityType.APPLICATION_MODAL);
                popup.add(createPopup());
                popup.setLocationRelativeTo(null);
                popup.setSize(new Dimension(500, 250)); // TODO check SHMIS for this issue
                popup.setVisible(true);
                Banana banana = bananas.get(bananas.size() - 1);
                // System.out.println(target.getSelectedRow() + " " + target.getSelectedColumn());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Auto-generated method stub
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        System.out.println(lsm.getMinSelectionIndex());

        if (lsm.getMinSelectionIndex() >= 1)
            lsm.clearSelection();
    }
}

class QueueTableModel extends AbstractTableModel {
    private String[] columnNames = { "int_a", "str_b" };
    private Queue bananas = new Queue(); // Contains references to bananas

    public QueueTableModel(Queue bananas) {
        this.bananas = bananas;
    }

    @Override
    public int getRowCount() {
        return bananas.getSize();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        long bananaIndex = bananas.get(rowIndex).getAppointmentID() - 1;
        // System.out.println(bananaIndex);
        Banana banane = Broski.bananas.get((int) bananaIndex);

        switch (columnIndex) {
            case 0:
                return banane.getA();
            case 1:
                return banane.getB();
        }

        return null;
    }
}

class ColorTable extends JTable {
    private TableModel tm;

    public ColorTable(TableModel tm) {
        this.tm = tm;
        setModel(tm);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        int modelRow = convertRowIndexToModel(row);
        String color = (String) getModel().getValueAt(row, 1);

        System.out.println(getModel());
        // System.out.println(c);

        if (!isRowSelected(row)) {
            c.setBackground(getBackground());
            c.setForeground(getForeground());

            if (row != 0)
                c.setForeground(Color.LIGHT_GRAY);

            if (color.equals("yellow"))
                c.setBackground(Color.decode("#FFF2CC"));
            else if (color.equals("green"))
                c.setBackground(Color.decode("#D9EAD3"));
        }

        return c;
    }
}