package com.company;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class Gridtest extends JFrame {
    public static Gridtest gridtest;

    public Gridtest() {
        super("Not Minesweeper");
        this.add(new Grid());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        FlatLightLaf.install();
        gridtest = new Gridtest();
    }
}
// https://stackoverflow.com/questions/2444004/how-do-we-show-the-gridline-in-gridlayout gridlayout gapz
class Grid extends JPanel implements ActionListener{
    public JPanel main = new JPanel();
    // public

    // Model = 5 by 2
    public final int ROWS = 4;
    public final int COLS = 7;

    public JButton addBig = new JButton("add big and remove");

    public JPanel[] days = new JPanel[ROWS];

    public JPanel monday = new JPanel();
    public JPanel tuesday = new JPanel();
    public JPanel wednesday = new JPanel();

    public JComponent[][] components = new JComponent[ROWS][COLS];
    public GridBagConstraints[][] constraints = new GridBagConstraints[ROWS][COLS];
    // public

    public Grid() {
        super(new GridBagLayout());

        for (int i = 0; i < ROWS; i++) {
            days[i] = new JPanel(new GridBagLayout());
        }

        // Default stuff
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = i;
                c.gridy = j;
                c.gridheight = 1;
                constraints[i][j] = c;
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (j == 0)
                    components[i][j] = new JLabel("DAY");
                else if (i == 0)
                    components[i][j] = new JLabel("TIME");
                else
                    components[i][j] = new JLabel("BRO");

                components[i][j].setPreferredSize(new Dimension(100, 20));
                components[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
                days[i].add(components[i][j], constraints[i][j]);
            }
        }

        addBig.addActionListener(this);
        this.add(addBig);

        monday = days[0];
        tuesday = days[1];
        wednesday = days[2];

        this.add(monday);
        this.add(tuesday);
        this.add(wednesday);
    }

    public void innit() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == addBig) {
            System.out.println("add a big cell and remove some random ones");
            this.remove(1);
            // System.out.println(tuesday);
            // tuesday = days[1];

            days[0].removeAll();

            for (int j = 0; j < COLS; j++) {
                if (j == 0)
                    components[0][j] = new JLabel("DAY");
                else {
                    components[0][j] = new JLabel("BRO");
                    components[0][j].setBackground(Color.GREEN);
                    components[0][j].setOpaque(true);
                }
                components[0][j].setPreferredSize(new Dimension(100, 20));
                components[0][j].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
                days[0].add(components[0][j], constraints[0][j]);
            }

            // monday = days[0];
            this.add(days[0], 1);
            this.revalidate();
            this.repaint();
        }
    }
}
