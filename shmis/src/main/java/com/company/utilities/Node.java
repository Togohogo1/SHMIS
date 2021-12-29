package com.company.utilities;

import com.company.classes.Appointment;

public class Node {
    private Appointment appointment;
    private Node next;
    private Node prev;

    public Node() {
        this (null, null, null);
    }

    public Node(Appointment appointment) {
        this.appointment = appointment;
    }

    public Node(Appointment appointment, Node next, Node prev) {
        this.appointment = appointment;
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

    public Appointment getAppointment() {
        return appointment;
    }
}
