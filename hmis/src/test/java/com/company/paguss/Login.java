package com.company.paguss;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel implements ActionListener {
    JButton button1 = new JButton("button 1");

    JButton button51 = new JButton("batton 1");
    JButton button2 = new JButton("2");

    public Login() {
        // JLabel lb = new JLabel();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.gray);
        GridBagConstraints c = new GridBagConstraints();
        button51.addActionListener(this);
        button2.addActionListener(this);
        // button1.setPreferredSize(new Dimension(40, 40));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        button1.setBackground(Color.GREEN);
        this.add(button1, c);

        // button1.setPreferredSize(new Dimension(40, 40));
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;
        this.add(button51, c);

        // c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridy = 2;
        c.weighty = 1;
        c.weightx = 1;
        this.add(button2, c);

        for (int i = 0; i < 7; i++) {
            c.gridy++;
            this.add(new JButton("-"), c);
        }
        System.out.println(c.gridy);
        JButton button3 = new JButton("3");
        // c.fill = GridBagConstraints.BOTH;
        // c.anchor = GridBagConstraints.PAGE_END;
        c.gridy++;
        c.weighty = 1;
        c.weightx = 1;
        this.add(button3, c);

        // this.setSize(200, 540);

        // JPanel topButtons = new JPanel();
        // topButtons.setLayout(new BoxLayout(topButtons, BoxLayout.Y_AXIS));
        // JButton button1 = new JButton("button 1");
        // JButton button2 = new JButton("button 2");
        // JButton button3 = new JButton("button 3");
        // topButtons.add(button1);
        // topButtons.add(button2);
        // topButtons.add(button3);
        // this.add(topButtons, BorderLayout.NORTH);


        // JPanel bottomButtons = new JPanel();
        // bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.Y_AXIS));
        JButton button4 = new JButton("button 4");
        // bottomButtons.add(button4);
        // this.add(bottomButtons, BorderLayout.SOUTH);
        // this.add(button4);
    }


    /**
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == button51) {
            System.out.println("1");
            button51.setBackground(Color.BLACK);
            // button51.setBorder(BorderFactory.createEmptyBorder());
        } if (e.getSource() == button2) {
            System.out.println("12");
            button51.setBackground(Color.ORANGE);
        }
    }
}
