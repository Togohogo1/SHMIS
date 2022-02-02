package com.company.utilities;

import com.company.classes.Appointment;

public class Node {
    private long appointmentID = -1;
    private Node next;
    private Node prev;

    public Node() {
        this.next = null;
        this.prev = null;
    }

    public Node(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Node(long appointmentID, Node next, Node prev) {
        this.appointmentID = appointmentID;
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

    public long getAppointmentID() {
        return appointmentID;
    }
}
