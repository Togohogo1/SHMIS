package com.company.utilities;

/**
 * Used in <code>DoublyLinkedList.java</code> and stores the appointment ID.
 */
public class Node {
    private long appointmentID = -1;
    private Node next;
    private Node prev;

    /**
     * Initializes a node pointing to <code>null</code>.
     */
    public Node() {
        this.next = null;
        this.prev = null;
    }

    /**
     * Initializes a node containing the appointment ID.
     *
     * @param appointmentID The appointment ID
     */
    public Node(long appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Returns the next node.
     *
     * @return the next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next node to a new node.
     *
     * @param next The new node
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Returns the previous node.
     *
     * @return the previous node
     */
    public Node getPrev() {
        return prev;
    }

    /**
     * Sets the previous node to a new node.
     *
     * @param prev the previous node
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    /**
     * Get a node's appointment ID.
     *
     * @return the node's appointment ID
     */
    public long getAppointmentID() {
        return appointmentID;
    }
}
