package programmers;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Programmers12906 {
    public static int[] solution(int[] arr) {
        Deque<Integer> deque = new LinkedList<>();

        for (int num : arr) {
            if (deque.isEmpty() || deque.peekLast() != num) {
                deque.add(num);
            }
        }

        return deque.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
//        int[] arr = {1, 1, 3, 3, 0, 1, 1};
        int[] arr = {4, 4, 4, 3, 3};

        System.out.println(Arrays.toString(solution(arr)));
    }
}
