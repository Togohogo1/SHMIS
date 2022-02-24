package com.company.utilities;

import org.json.simple.JSONArray;

/**
 * My own implementation of a doubly linked list.
 */
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    /**
     * Initializes an empty doubly linked list with sentinel nodes as the head and
     * tail.
     */
    public DoublyLinkedList() {
        head = new Node();
        tail = new Node();
        size = 0;

        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Inserts a node at the front of the doubly linked list
     *
     * @param node Node to be inserted
     */
    public void insertFront(Node node) {
        Node next = head.getNext();

        head.setNext(node);
        node.setPrev(head);
        node.setNext(next);
        next.setPrev(node);

        size++;
    }

    /**
     * Inserts a node at the fact of the doubly linked list.
     *
     * @param node Node to be inserted
     */
    public void insertBack(Node node) {
        Node prev = tail.getPrev();

        tail.setPrev(node);
        node.setNext(tail);
        node.setPrev(prev);
        prev.setNext(node);

        size++;
    }

    /**
     * Removes a node from the front of the doubly linked list. Returns if the size of the linked list is 0.
     */
    public void removeFront() {
        if (head.getNext().getAppointmentID() == -1)
            return;

        Node next = head.getNext();
        Node nnext = next.getNext();

        next.setPrev(null);
        next.setNext(null);
        head.setNext(nnext);
        nnext.setPrev(head);

        size--;
    }

    /**
     * Removes a node from the back of the doubly linked list. Returns if the size of the linked list is 0.
     */
    public void removeBack() {
        if (tail.getPrev().getAppointmentID() == -1)
            return;

        Node prev = tail.getPrev();
        Node pprev = prev.getPrev();

        prev.setPrev(null); // Removing it = setting it to null
        prev.setNext(null);
        pprev.setNext(tail);
        tail.setPrev(pprev);

        size--;
    }

    /**
     * Removes a specified value from the doubly linked list. Precondition is that all values in the linked list are unique.
     *
     * @param id Value to be removed
     */
    public void remove(long id) {
        Node node = head;
        int idx = 1;  // The 0th Node is a sentinel

        while (idx++ <= size) {
            node = node.getNext();

            if (node.getAppointmentID() == id) {
                node.getPrev().setNext(node.getNext());
                node.getNext().setPrev(node.getPrev());
                node.setNext(null);
                node.setPrev(null);
                break;
            }
        }

        size--;
    }

    /**
     * Returns the value of a specified index in the doubly linked list.
     *
     * @param idx The index
     * @return The node at index <code>idx</code>
     */
    public Node get(int idx) {
        Node node = head;
        idx++;  // the 0th Node is a sentinel

        while (idx-- != 0) {
            node = node.getNext();
        }

        return node;
    }

    /**
     * Returns the size of the doubly linked list.
     *
     * @return Size of the doubly linked list
     */
    public int getSize() {
        return size;
    }

    /**
     * Initializes the doubly linked list with values from a <code>JSONArray</code>.
     *
     * @param arr The <code>JSONArray</code> to be read from
     */
    public void fromJSONArray(JSONArray arr) {
        for (Object id : arr) {
            insertBack(new Node((long) id));
        }
    }

    /**
     * Convertes the doubly linked list to a <code>JSONArray</code>.
     *
     * @return The converted <code>JSONArray</code>
     */
    public JSONArray toJSONArray() {
        JSONArray queue = new JSONArray();
        Node node = head;

        while (node.getNext().getAppointmentID() != -1) {
            node = node.getNext();
            queue.add(node.getAppointmentID());
        }

        return queue;
    }
}
