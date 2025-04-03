package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Programmers42626 {
    // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 가장 작은 숫자가 맨 앞으로 갈 것
        int shuffleCnt = 0; // 섞은 횟수.

        Arrays.stream(scoville).forEach(pq::add); // 우선순위 큐에 모든 원소 삽입

        while (!pq.isEmpty()) {
            if (pq.peek() >= K) return shuffleCnt; // 더이상 섞을 필요가 없으므로 지금까지 섞은 횟수 반환
            else if (pq.size() == 1) break; // 음식이 하나 남았는데 스코빌지수가 K 미만 -> 반복문 종료

            int smallest = pq.poll(); // 맨 앞 음식
            int nextSmallest = pq.poll(); // 두번째 음식. 위의 조건식들로 인해, 해당 시점에선 pq.size() > 1 임이 확인됐으므로, NPE는 발생하지 않는다.

            pq.add(smallest + nextSmallest * 2);

            shuffleCnt++;
        }

        return -1; // 반복문이 종료됐다는 것은 스코빌 지수를 K로 만들지 못한다는 뜻이므로, -1 반환
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(solution(scoville, K));
    }
}
