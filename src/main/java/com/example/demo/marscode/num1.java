package com.example.demo.marscode;

import java.util.HashSet;
import java.util.Set;

public class num1 {
    public static int solution(int[] cards) {
        // Edit your code here
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < cards.length; i++) {
            if (set.contains(cards[i])) {
                set.remove(cards[i]);
            } else {
                set.add(cards[i]);
            }
        }

        return set.iterator().next();
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution(new int[] { 1, 1, 2, 2, 3, 3, 4, 5, 5 }) == 4);
        System.out.println(solution(new int[] { 0, 1, 0, 1, 2 }) == 2);
    }
}