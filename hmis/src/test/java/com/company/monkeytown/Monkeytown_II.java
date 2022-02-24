package com.company.monkeytown;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.namespace.QName;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

/*
===== table for split view section =====
[x] custom table model for arraylist

[x] single slick row to open banana information
    [-] double click row to open dialogue
[-] double click row to open dialogue
[-] ^ edit iq and name, submit or cancel
[-] ^^ delete the row

[-] redisplay table after rearranging (simulate shuffle)
[-] filter text from oracle website
    - swaps array with filtered array
    - boolean flag = editable (false when searched, or items in table < original)
    - go button and a reset button (click reset reswaps the arrays)
*/

public class Monkeytown_II extends JFrame {
    public Monkeytown_II() {
        super("Monkeytown II");
        this.add(new Broskus());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.setVisible(true);
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        FlatLightLaf.install();
        new Monkeytown_II();
    }
}

class Broskus extends JPanel implements MouseListener, ListSelectionListener, ActionListener {
    private int selectedRow;

    private ArrayList<Monkey> monkes = new ArrayList<>();
    private ArrayList<Banana> bananas = new ArrayList<>();

    private JSplitPane splitPane;

    private MonkeyTableModel monkeyTableModel;
    private BananaTableModel bananaTableModel;
    private JTable monkeyTable;
    private JTable bananaTable;

    private JDialog editPopup;
    private JTextField newIq;
    private JTextField newName;
    private JButton confirm = new JButton("confirm edits");;
    private JButton delete = new JButton("delete the mf");;

    private JTextField filter;
    private JButton search;

    public Broskus() {
        confirm.addActionListener(this);
        delete.addActionListener(this);

        filter = new JTextField();
        search = new JButton("Search");
        search.addActionListener(this);

        monkes.add(new Monkey());
        monkes.add(new Monkey(111, 50, "average monke", new ArrayList<>(Arrays.asList(1))));
        monkes.add(new Monkey(222, 100, "smartfella", new ArrayList<>(Arrays.asList(1, 2))));
        monkes.add(new Monkey(555, -100, "fartsmella", new ArrayList<>(Arrays.asList(1, 2, 3, 4))));
        monkes.add(new Monkey(420, 69, "memer", new ArrayList<>(Arrays.asList(5, 6))));

        bananas.add(new Banana(1, "soggy"));
        bananas.add(new Banana(2, "sweet"));
        bananas.add(new Banana(3, "ripe"));
        bananas.add(new Banana(4, "old"));
        bananas.add(new Banana(5, "stinky"));
        bananas.add(new Banana(6, "yellow"));

        monkeyTableModel = new MonkeyTableModel(monkes);
        bananaTableModel = new BananaTableModel(new ArrayList<>());

        monkeyTable = new JTable(monkeyTableModel);
        monkeyTable.getTableHeader().setReorderingAllowed(false);
        monkeyTable.addMouseListener(this);
        monkeyTable.getSelectionModel().addListSelectionListener(this);
        monkeyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        monkeyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        bananaTable = new JTable(bananaTableModel);
        bananaTable.getTableHeader().setReorderingAllowed(false);
        bananaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane embed1 = new JScrollPane(monkeyTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane embed2 = new JScrollPane(bananaTable);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, embed1, embed2);

        this.add(filter);
        this.add(search);
        this.add(splitPane);
    }

    public JPanel createPopup(Monkey monkey) {
        JPanel popup = new JPanel();
        newIq = new JTextField(Integer.toString(monkey.getIq()));
        newName = new JTextField(monkey.getName());

        popup.add(newIq);
        popup.add(newName);
        popup.add(confirm);
        popup.add(delete);

        return popup;
    }

    public ArrayList<Banana> getSpecificBananas(Monkey monkey) {
        ArrayList<Banana> _bananas = new ArrayList<>();

        for (int i : monkey.getBanana()) {
            _bananas.add(this.bananas.get(i-1));
        }

        return _bananas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow(); // select a row
        int column = target.getSelectedColumn(); // select a column


        if (e.getClickCount() == 2) {
            Monkey monkey = monkes.get(row);
            editPopup = new JDialog(null, "Monkeytown", Dialog.ModalityType.APPLICATION_MODAL);
            editPopup.add(createPopup(monkey));
            editPopup.setLocationRelativeTo(null);
            editPopup.setSize(new Dimension(500, 250));
            editPopup.setVisible(true);
            System.out.println("double click " + row + ", " + column);
        } else {
            // bananaTableModel.setBananas(getSpecificBananas(monkes.get(row)));
            // bananaTableModel.fireTableDataChanged();
            // System.out.println("single click " + row + ", " + column);
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

        int firstIndex = lsm.getMinSelectionIndex();
        int lastIndex = lsm.getMaxSelectionIndex();
        // System.out.println(firstIndex + ", " + lastIndex);

        if (firstIndex >= 0) {
            selectedRow = firstIndex;
            System.out.println("changes");
            bananaTableModel.setBananas(getSpecificBananas(monkes.get(firstIndex)));
            bananaTableModel.fireTableDataChanged();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == confirm) {
            int _newIq = Integer.parseInt(newIq.getText());
            String _newName = newName.getText();
            monkes.get(selectedRow).setIq(_newIq);
            monkes.get(selectedRow).setName(_newName);
            monkeyTableModel.fireTableDataChanged();
            editPopup.setVisible(false);
            System.out.println("confirm edits " +  _newIq + " " + _newName);

        } else if (e.getSource() == delete) {
            int n = JOptionPane.showConfirmDialog(null, "You really want to do this bro????", "Confirm Monkey Extermination", JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_OPTION == n) {
                monkes.remove(selectedRow);
                monkeyTableModel.fireTableDataChanged();  // maybe change to another firexxx method
                editPopup.setVisible(false);
            }

            System.out.println("delete the mf");
        } else if (e.getSource() == search) {
        }
    }
}

class MonkeyTableModel extends AbstractTableModel {
    private String[] columnNames = {"id", "iq", "name", "bananas"};
    private ArrayList<Monkey> monkes;
    private ArrayList<Monkey> tempMonkes; // Used for searching purposes

    public MonkeyTableModel(ArrayList<Monkey> monkes) {
        this.monkes = monkes;
    }

    @Override
    public int getRowCount() {
        if (monkes == null)
            return 0;

        return monkes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length - 1;  // dont want the last col
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Monkey monkey = monkes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return monkey.getId();
            case 1:
                return monkey.getIq();
            case 2:
                return monkey.getName();
        }

        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}

class BananaTableModel extends AbstractTableModel {
    private String[] columnNames = {"int_a", "str_b"};
    private ArrayList<Banana> bananas;

    public BananaTableModel(ArrayList<Banana> bananas) {
        this.bananas = bananas;
    }

    @Override
    public int getRowCount() {
        if (bananas == null)
            return 0;

        return bananas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Banana banana = bananas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return banana.getA();
            case 1:
                return banana.getB();
        }

        return null;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setBananas(ArrayList<Banana> bananas) {
        this.bananas = bananas;
    }
}