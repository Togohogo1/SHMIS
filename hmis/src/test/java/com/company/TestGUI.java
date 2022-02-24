package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.company.pages.FontColor;
import com.company.paguss.Login;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

/*
JTable todo list
[ ] display colours
[ ] have specific rows not be editable
[ ] update a row
*/


public class TestGUI {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FlatLightLaf.install();
        // UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Microsoft JhengHei UI", Font.PLAIN, 12));
        // UIManager.put("Button.arc", 0);
        Shmes s = new Shmes();
    }
}

class Shmes extends JFrame implements ActionListener {
    public JRadioButton choix;
    public JRadioButton choix2 = new JRadioButton("Joshgone gae?");
    public JRadioButton choix3 = new JRadioButton("Imposter sus?");
    public JRadioButton choix4 = new JRadioButton("What the dog doin?");
    public JRadioButton choix5 = new JRadioButton("Yes or no?");
    public JTextField textArea = new JTextField("Bing Chillinmg");
    public JScrollPane embed = new JScrollPane(textArea);
    public JButton butt = new JButton("CLICK TO SUBMIT");
    public JDialog jd;
    public JButton openDialog = new JButton("Open the dialog bro");
    public JButton tableTester = new JButton("Click to test table dialogue");
    public JButton addRow = new JButton("add row");;

    public JDialog tableDialog;
    public JTable table;
    public JScrollPane tabContainer;
    public SusTableModel tableModel;

    public Shmes() {
        super("Simple Healthcare Management Interface for Specialists");
        this.setSize(1200, 675);

        // ============== TabbedPane Dialog ==============
        butt.addActionListener(this);

        jd = new JDialog(null, "Monkeytown", Dialog.ModalityType.APPLICATION_MODAL);
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        JPanel p3 = new JPanel(new GridBagLayout());
        JPanel p4 = new JPanel(new GridBagLayout());

        choix2.setFont(FontColor.H1);

        choix3.setSelected(true);
        choix3.setEnabled(false);

        p1.add(choix2);
        p2.add(choix3);
        p3.add(choix4);
        p4.add(textArea);

        JTabbedPane jp = new JTabbedPane();
        jp.addTab("title1", p1);
        jp.addTab("title2", p2);
        jp.addTab("title3", p3);
        jp.addTab("title4", p4);

        JLabel lab = new JLabel("I WILL GET A 140 TODAY IN TYPERACER");
        lab.setFont(new Font("Comic Sans MS", Font.BOLD, 42));
        JPanel p = new JPanel();
        p.add(lab);  // Adding JLABEL
        p.add(jp);  // Adding ~~jake paul~~ JTabbedPane


        p.add(butt);  // Adding the button
        jd.add(p);

        // ============== TabbedPane Dialog ==============




        // ============== Table Dialog ==============
        addRow.addActionListener(this);
        ArrayList<Monkey> monkeytown = new ArrayList<>();
        monkeytown.add(new Monkey());
        monkeytown.add(new Monkey());
        monkeytown.add(new Monkey());
        monkeytown.add(new Monkey(21, -99999, "whats9+10", true));
        tableModel = new SusTableModel(monkeytown);
        JPanel tabDialogPanel = new JPanel();

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // TableColumn column = tab.getColumnModel().getColumn(2);
        // column.setPreferredWidth(column.getPreferredWidth());

        tabContainer = new JScrollPane(table);
        // tabContainer.setPreferredSize(new Dimension(300, 150));//   <- able to set its dimensiton
        tabDialogPanel.add(tabContainer);

        tableDialog = new JDialog(tableDialog, "Tabletown", Dialog.ModalityType.APPLICATION_MODAL);
        tabDialogPanel.add(addRow);
        tableDialog.add(tabDialogPanel);



        // ============== Table Dialog ==============





        JPanel f = new JPanel();
        JTextArea textArea = new JTextArea(5, 20);
        f.add(textArea);
        f.add(new JTextField());
        String[] sus = {"a", "b", "c", "d", "e"};
        JComboBox com = new JComboBox(sus);
        f.add(com);

        openDialog.addActionListener(this);
        f.add(openDialog);

        tableTester.addActionListener(this);
        f.add(tableTester);


        // f.add(new JButton("button moment"));

        // this.setLayout(new GridBagLayout());
        // GridBagConstraints c = new GridBagConstraints();

        // Login login = new Login();

        // c.fill = GridBagConstraints.BOTH;
        // c.gridx = 0;
        // c.weightx = 0.2;
        // c.weighty = 1;
        // this.add(login, c);


        // JPanel dummy = new JPanel(new GridBagLayout());
        // dummy.setBackground(Color.cyan);
        // c.gridx = 1;
        // c.weightx = 0.8;
        // this.add(dummy, c);
        this.add(f);
        this.setLocationRelativeTo(null);  // Center of the screen when opened
        // this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butt) {
            // TODO Auto-generated method stub
            System.out.println(
                "" +
                choix2.isSelected() + " " +
                choix3.isSelected() + " " +
                textArea.getText() + " " +
                choix5.isSelected()
            );
            jd.setVisible(false);
        } else if (e.getSource() == openDialog) {
            showDialog(jd);
        } else if (e.getSource() == tableTester) {
            showDialog(tableDialog);
        } else if (e.getSource() == addRow) {
            tableModel.monkes.set(0, new Monkey(99, 99, "eueeee", true));
            tableModel.fireTableCellUpdated(0, 0);
        }
    }

    public void showDialog(JDialog jd) {
        jd.setSize(1000, 500);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
    }

    public void showTableDialog(JDialog jd) {
        jd.setSize(1000, 500);
        jd.setLocationRelativeTo(null);
        jd.setVisible(true);
    }

}

class SusTableModel extends AbstractTableModel {
    private String[] columnNames = {"Age", "Iq", "Name", "Is Banana Monkey?"};
    public ArrayList<Monkey> monkes;

    public SusTableModel(ArrayList<Monkey> monkes) {
        this.monkes = monkes;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        if (monkes == null)
            return 0;

        return monkes.size();
    }

    public Object getValueAt(int row, int col) {
        if (col == 0)
            return monkes.get(row).getAge();
        else if (col == 1)
            return monkes.get(row).getIQ();
        else if (col == 2)
            return monkes.get(row).getName();
        else if (col == 3)
            return monkes.get(row).getBanana();
        return null;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        if (col == 0)
            monkes.get(row).setAge(Integer.parseInt((String)value));
        else if (col == 1)
            monkes.get(row).setIQ(Integer.parseInt((String)value));
        else if (col == 2)
            monkes.get(row).setName((String)value);
        else if (col == 3)
            monkes.get(row).setBanana((boolean)value);

        fireTableCellUpdated(row, col);
    }
}

class Monkey {
    private int age;
    private int iq;
    private String name;
    private boolean isBananaMonkey;

    public Monkey() {
        this.age = 10;
        this.iq = 100;
        this.name = "Monke";
        this.isBananaMonkey = false;
    }

    public Monkey(int age, int iq, String name, boolean isBananaMonkey) {
        this.age = age;
        this.iq = iq;
        this.name = name;
        this.isBananaMonkey = isBananaMonkey;
    }

    public int getAge() {
        return age;
    }

    public int getIQ() {
        return iq;
    }

    public String getName() {
        return name;
    }

    public boolean getBanana() {
        return isBananaMonkey;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIQ(int iq) {
        this.iq = iq;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBanana(boolean isBananaMonkey) {
        this.isBananaMonkey = isBananaMonkey;
    }
}
