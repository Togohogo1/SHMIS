package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UnrefButton extends JFrame {
    public UnrefButton() {
        super("unref jbutton");
        this.add(new Losedow());  // Default (changed to program for testing purposes)
        this.setSize(1024, 576);
        this.setLocationRelativeTo(null);  // Center of the screen when opened
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new UnrefButton();
    }
}

class Losedow extends JPanel {
    public static int incr = 0;

    public Losedow() {
        this.add(new JButton(new AbstractAction("___________") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("BROOOOOOO");
                ayo();
            }
        }));
    }

    public void ayo() {
        // how would i do this on initialization with the data structures present
        /*
        just initialize the table when switch over to tab
        List<Appts> {1, 2, 3, 4, 5, 6}
            App.dsm?.queryAppt(idx)
            - able to get xray, ult, other, notes info to display
            - able to get start day as an int and colspan
        JButton [][]
        GBC [][]


        */
        anonAdd();
        rebaint();
    }

    public void anonAdd() {
        this.add(new JButton(new AbstractAction("add negative iq" + ++incr) {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"button about to get deleted");
                // [Solved] how to remove this dialog thing
                delete((JButton)e.getSource());
                rebaint();
            }
        }));
    }

    public void delete(JButton b) {
        this.remove(b);
    }

    public void rebaint() {
        this.revalidate();
        this.repaint();
    }

}