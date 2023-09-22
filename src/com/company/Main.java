package com.company;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        new DatesAnalysis().runProcess(100);

        // test
//        PriorityQueueOfBinaryHeap<Integer> q = new PriorityQueueOfBinaryHeap<>();
//        q.insert(1, 6);
//        q.insert(2, 4);
//        q.insert(9, 5);
//        q.insert(10, 7);
//        q.insert(12, 1);
//
//        q.increase(2, 9);
//        q.insert(30, 30);
//
//        while(q.size() > 0) {
//            System.out.println(q.extractMax());
//        }

//        PriorityQueueOfLinkedList<Integer> l = new PriorityQueueOfLinkedList<>();
//        l.insert(1, 6);
//        l.insert(2, 3);
//        l.insert(9, 5);
//        l.insert(10, 7);
//        l.insert(12, 1);
//
//        l.increase(12, 2);
//
//        for (int i = 0; i < 3; i++) {
//            System.out.println(l.extractMax());
//        }

    }
}
