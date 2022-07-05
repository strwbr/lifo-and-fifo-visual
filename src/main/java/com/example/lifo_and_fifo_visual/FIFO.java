package com.example.lifo_and_fifo_visual;

public class FIFO {
    private Node top, tail;
    private int size;

    public FIFO() {
        this.size = 0;
        this.top = null;
        this.tail = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    // добавление в конец
    public void enqueue(String data) {
        Node add = new Node(data, null);
        if (isEmpty()) {
            tail = add;
            top = tail;
        } else {
            tail.next = add;
            tail = tail.next;
        }
        this.size++;
    }

    // удаление из начала
    public boolean dequeue() {
        if (isEmpty())
            return false;
        // если 1 элемент, то сбрасываем в начальное состояние
        if (this.size==1) {
            top = null;
            tail = null;
        } else {
            top=top.next;
        }
        this.size--;
        return true;
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
        return p.data;    }

    // получение элемента из начала
    public String peek() {
        if (isEmpty())
            return null;

        return top.data;
    }
}
