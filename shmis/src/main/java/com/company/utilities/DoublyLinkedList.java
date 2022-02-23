package com.company.utilities;

import org.json.simple.JSONArray;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = new Node();
        tail = new Node();
        size = 0;

        head.setNext(tail);
        tail.setPrev(head);
    }

    public void insertFront(Node node) {
        Node next = head.getNext();

        head.setNext(node);
        node.setPrev(head);
        node.setNext(next);
        next.setPrev(node);

        size++;
    }

    public void insertBack(Node node) {
        Node prev = tail.getPrev();

        tail.setPrev(node);
        node.setNext(tail);
        node.setPrev(prev);
        prev.setNext(node);

        size++;
    }

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

    public void removeBack() {
        if (tail.getPrev().getAppointmentID() == -1)
            return;

        Node prev = tail.getPrev();
        Node pprev = prev.getPrev();

        prev.setPrev(null);  // Removing it = setting it to null
        prev.setNext(null);
        pprev.setNext(tail);
        tail.setPrev(pprev);

        size--;
    }

    // Precondition: all IDs in queue are unique
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

    public Node get(int idx) {
        Node node = head;
        idx++;  // the 0th Node is a sentinel

        while (idx-- != 0) {
            node = node.getNext();
        }

        return node;
    }

    public void fromJSONArray(JSONArray arr) {
        for (Object id : arr) {
            insertBack(new Node((long) id));
        }
    }

    public JSONArray toJSONArray() {
        JSONArray queue = new JSONArray();

        // Traverse the queue and add items in the JSONArray
        Node node = head;

        while (node.getNext().getAppointmentID() != -1) {
            node = node.getNext();
            queue.add(node.getAppointmentID());
        }

        return queue;
    }

    // General purpose traverse function
    public void traverse() {
        Node node = head;

        while (node.getNext().getAppointmentID() != -1) {
            node = node.getNext();
            System.out.println(node.getAppointmentID());
        }
    }

    public int getSize() {
        return size;
    }
}
