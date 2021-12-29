package com.company.utilities;

public class DoublyLinkedList {
    public Node head;
    public Node tail;
    public int size;

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
        if (head.getNext().getAppt() == null)
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
        if (tail.getPrev().getAppt() == null)
            return;

        Node prev = tail.getPrev();
        Node pprev = prev.getPrev();

        prev.setPrev(null);
        prev.setNext(null);
        pprev.setNext(tail);
        tail.setPrev(pprev);

        size--;
    }

    public void traverse() {
        Node node = head;

        while (node.getNext().getAppt() != null) {
            node = node.getNext();
        }
    }
}
