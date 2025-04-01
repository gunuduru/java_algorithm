package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Programmers1845 {
    // nums.size() == N -> N/2개를 골라야 함
    public static int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).distinct().forEach(set::add);

        // 고유한 포켓몬의 개수가 N/2보다 작거나 같다 -> 고유한 포켓몬의 개수를 반환
        // 아니라면, N/2를 반환
        return Math.min(nums.length / 2, set.size());
    }

    public static void main(String[] args) {
//        int[] nums = {3, 1, 2, 3};
//        int[] nums = {3,3,3,2,2,4};
        int[] nums = {3,3,3,2,2,2};
        System.out.println(solution(nums));
    }
}
