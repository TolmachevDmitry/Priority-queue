package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueueOfLinkedList<T extends Comparable<T>> {

    private class Node {

        public T value;
        public double priority;
        public Node next;

        Node(T value, double priority, Node next) {
            this.value = value;
            this.priority = priority;
            this.next = next;
        }

        Node(T value, double priority) {
            this(value, priority, null);
        }
    }


    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void insert(T value, double priority) {
        if (size == 0) {
            head = tail = new Node(value, priority);
        } else {
            positionSearch(value, priority, false);
        }
        size++;
    }

    public T extractMax() {
        T elem = head.value;
        head = head.next;
        size--;

        return elem;
    }

    public void increase(T value, double upDatedPriority) {
        if (size == 1) {
            if (head.value.compareTo(value) == 0) {
                head.priority = upDatedPriority;
            }
        } else if (head.value.compareTo(value) == 0) {
            head.priority = upDatedPriority;
        } else {
            positionSearch(value, upDatedPriority, true);
        }
    }

    public void test() {
        Node curr = head;
        for (int i = 0; i < size; i++) {
            System.out.print(curr.value + " - " + curr.priority + ((i != size - 1) ? (", ") : (";")));
            curr = curr.next;
        }
        System.out.println();
    }


    private int positionSearch(T value, double priority, boolean delete) {
        Node curr = head;
        boolean added = false;
        boolean deleted = !delete;

        // В итоге:
        // deleted --> true - если метод вызывается из insert(), т.е. удаление не нужно
        // deleted --> false - если метод вызывается из increase(), т.е. удаление ещё не сделано

        if (value.compareTo(head.value) == 0 && !deleted) {
            head.value = value;
            head.priority = priority;
            return 1;
        }

        for (int i = 0; i < size; i++) {
            // Вставка нового элемента (старый элемент с изменённым приоритетом положим новым)
            if (i == 0) {
                if (!added && priority > curr.priority) {
                    head = new Node(value, priority);
                    head.next = curr;
                    added = true;
                    curr = head;
                }
            } else {
                if (!added && priority > curr.next.priority) {
                    curr.next = new Node(value, priority, curr.next);
                    added = true;
                }
                curr = curr.next;
            }

            // Удаление значения с устаревшим элементом
            if (!deleted && added && curr.next.value.compareTo(value) == 0) {
                curr.next = curr.next.next;
                deleted = true;
            }
            if (added && deleted) {
                break;
            }
        }

        // Для вставки элемента с самым низким приоритетом
        if (!added) {
            tail.next = new Node(value, priority);
            tail = tail.next;
        }

        return 1;
    }

    public int size() {
        return size;
    }

    public ArrayList<T> getValues() {
        Node curr = head;
        ArrayList<T> answer = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            answer.add(curr.value);
            curr = curr.next;
        }
        return answer;
    }
    public ArrayList<Double> getPriorities() {
        Node curr = head;
        ArrayList<Double> answer = new ArrayList<Double>();
        for (int i = 0; i < size; i++) {
            answer.add(curr.priority);
            curr = curr.next;
        }
        return answer;
    }

}
