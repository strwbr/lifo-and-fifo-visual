package com.example.lifo_and_fifo_visual;

public class Node {
    public String data;
    public Node next;

    public Node() {
        this.data = "";
        this.next = null;
    }

    public Node(String data) {
        this.data = data;
        this.next = null;
    }

    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }
}