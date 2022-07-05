package com.example.lifo_and_fifo_visual;

public class LIFO {
    private Node top; // голова
    private int size; // кол-во элементов

    public LIFO() {
//        this.top = new Node();
        this.size = 0;
        this.top = null; // голова указывает на null
    }
    public int size() {
        return size;
    }

    // проверка на пустоту
    public boolean isEmpty() {
        return top == null;
    }

    // добавление в начало
    public void push(String data) {
//        Node add = new Node(data);
//        add.next = top;
        top = new Node(data, top);
        this.size++;
    }

    // удаление элемента из начала
    public boolean pop() {
        if (isEmpty())
            return false;
        top = top.next;
        this.size--;
        return true;
    }

    // получение элемента из начала
    public String peek() {
        if (isEmpty())
            return null;
        return top.data;
    }

    public String getByNum(int num) {
        if(num < 0 || num > this.size -1) return null;

        Node p = top;
        int i = 0;
        while(p!=null) {
            if(i==num) break;
            p = p.next;
            i++;
        }
        return p.data;
    }

    public boolean clear() {
        if(isEmpty())
            return false;
        Node p = top;
        while(p!=null) {
            pop();
            p = p.next;
            size--;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node p = top;
        while(p!=null) {
            str.append(p.data);
            str.append(", ");
            p = p.next;
        }
        return str.toString();
    }
}
