package com.company;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public class DatesAnalysis {

    class Node {
        int value;
        double priority;

        Node(int value, double priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    final Random random = new Random();
    private int n;

    private ArrayList<Node> createDates() {
        ArrayList<Node> list = new ArrayList<Node>();

        for (int i = 0; i < n; i++) {
            list.add(new Node(random.nextInt(100), random.nextInt(100)));
        }

        return list;
    }

    public void runProcess(int countN) {
        n = countN;

        int[] insert_list = new int[n];
        int[] insert_heap = new int[n];
        int[] extractMax_list = new int[n];
        int[] extractMax_heap = new int[n];
        int[] increase_list = new int[n];
        int[] increase_heap = new int[n];

        PriorityQueueOfLinkedList<Integer> listQueue = new PriorityQueueOfLinkedList<>();
        PriorityQueueOfBinaryHeap<Integer> heapQueue = new PriorityQueueOfBinaryHeap<>();


        // Замеры
        ArrayList<Node> dates = createDates();
        // insert
        for (int i = 0; i < n; i++) {
            Node elem = dates.get(i);
            long start = System.nanoTime();
            listQueue.insert(elem.value, elem.priority);
            long finish = System.nanoTime();
            insert_list[i] = (int) (finish - start);

            start = System.nanoTime();
            heapQueue.insert(elem.value, elem.priority);
            finish = System.nanoTime();
            insert_heap[i] = (int) (finish - start);
        }

        // increase
        ArrayList<Integer> valuesFromList = listQueue.getValues();
        ArrayList<Double> prioritiesFromList = listQueue.getPriorities();
        ArrayList<Integer> valuesFromHeap = heapQueue.getValues();
        ArrayList<Double> prioritiesFromHeap = heapQueue.getPriorities();
        for (int i = 0; i < n; i++) {
            long start = System.nanoTime();
            listQueue.increase(valuesFromList.get(i), prioritiesFromList.get(i) + random.nextInt(50));
            long finish = System.nanoTime();
            increase_list[i] = (int) (finish - start);

            start = System.nanoTime();
            heapQueue.increase(valuesFromHeap.get(i), prioritiesFromHeap.get(i) + random.nextInt(50));
            finish = System.nanoTime();
            increase_heap[i] = (int) (finish - start);
        }


        // extraMax
        for (int i = 0; i < n; i++) {
            long start = System.nanoTime();
            listQueue.extractMax();
            long finish = System.nanoTime();
            extractMax_list[i] = (int) (finish - start);

            start = System.nanoTime();
            heapQueue.extractMax();
            finish = System.nanoTime();
            extractMax_heap[i] = (int) (finish - start);
        }


        System.out.println("PriorityQueue of List");
        System.out.println("n      insert()     increase()    extractMax()");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "       " + insert_list[i] + "       " + increase_list[i]
                    + "       " + extractMax_list[n - 1 - i]);
        }
        System.out.println();

        System.out.println("PriorityQueue of Heap");
        System.out.println("n      insert()     increase()    extractMax()");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "       " + insert_heap[i] + "       "
                    + increase_heap[i] + "       " + extractMax_heap[n - 1 - i]);
        }

    }

}
