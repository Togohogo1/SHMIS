package com.company.pages;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class Login extends JPanel {
    public Login() {
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);

        JPanel dummy = new JPanel();
        dummy.add(new JButton("click me1"));
        JPanel dummy2 = new JPanel();
        dummy2.add(new JButton("click me2"));
        JPanel dummy3 = new JPanel();
        dummy3.add(new JButton("click me3"));

        tabs.add("", dummy);
        tabs.add("", dummy2);
        tabs.add("", dummy3);

        JLabel l1 = new JLabel("tab1");
        l1.setFont(new Font("Roboto", Font.PLAIN, 24));
        l1.setBorder(new EmptyBorder(5, 5, 5, 5));
        l1.setHorizontalAlignment(SwingConstants.LEFT);
        l1.setPreferredSize(new Dimension(200, 200));
        JLabel l2 = new JLabel("tab      2");
        l2.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel l3 = new JLabel("tab____________________3");
        l3.setHorizontalAlignment(SwingConstants.LEFT);

        tabs.setTabComponentAt(0, l1);
        tabs.setTabComponentAt(1, l2);
        tabs.setTabComponentAt(2, l3);

        this.setLayout(new GridLayout(1, 1));
        this.add(tabs);
    }
}
