package com.company.utilities;

import com.company.App;
import com.company.classes.Appointment;

public class Node {
    private Appointment appt;  // TODO check uses of appointment
    private Node next;
    private Node prev;

    public Node() {
        this (null, null, null);
    }

    public Node(Appointment appt) {
        this.appt = appt;
    }

    public Node(Appointment appt, Node next, Node prev) {
        this.appt = appt;
        this.next = next;
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Appointment getAppt() {
        return appt;
    }
}
