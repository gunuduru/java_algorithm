package programmers;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Programmers42586 {
    /**
     * 작업 정보를 담는다.
     * * progress: 해당 작업의 작업진도
     * * speed: 해당 작업의 작업속도
     */
    static class Task {
        public int progress;
        public int speed;

        public Task(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }

        /**
         * days가 지난 시점에 해당 task가 완료된 상태인지
         */
        public boolean isFinished(int days) {
            return progress + speed * days >= 100;
        }
    }

    /**
     * 어떤 기능이 먼저 완성됐더라도, 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능
     * -> 뒤에께 먼저 완성됐으면 그걸 queue에 넣고, 앞에꺼 완성되면 ㄱㄱ
     */
    public static int[] solution(int[] progresses, int[] speeds) {
        int days = 1; // 며칠 지났는지. 처음은 1일차부터
        Deque<Task> queue = new LinkedList<>();
        List<Integer> result = new LinkedList<>(); // 리턴할 결과값 배열

        // progresses와 speed의 크기는 같을 것이므로, i의 상한값은 progresses.length로 정함
        for (int i = 0; i < progresses.length; i++) {
            queue.add(new Task(progresses[i], speeds[i]));
        }

        while (!queue.isEmpty()) {
            // 오늘이 끝나면 완료되는 작업의 수.
            int finishedCount = 0;

            // 1. queue의 front를 확인하여, 현재 날짜에 완료된 작업인지 확인 -> 완료되지 않은 (= task.progress + tast.speed*days < 100) 작업이 나올때까지 반복
            while (!queue.isEmpty() && queue.getFirst().isFinished(days)) {
                finishedCount++;
                queue.removeFirst();
            }

            if (finishedCount != 0) result.add(finishedCount);
            days++; // 다음 반복문에서는 날짜를 1 증가 (다음날)
        }


        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
//        int[] progresses = {93, 30, 55};
        int[] progresses = {95, 90, 99, 99, 80, 99};
//        int[] speeds = {1, 30, 5};
        int[] speeds = {1, 1, 1, 1, 1, 1,};
        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }
}
