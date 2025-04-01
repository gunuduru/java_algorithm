package programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers42576 {
    public static String solution(String[] participant, String[] completion) {
        Map<String, Integer> mapNameToCount = new HashMap<>(); // mapNameToCount["david"] = 2 -> 완주자 명단에 david가 2명 있음
        for (String completionName : completion) {
            if (!mapNameToCount.containsKey(completionName)) {
                mapNameToCount.put(completionName, 1);
            } else {
                mapNameToCount.put(completionName, mapNameToCount.get(completionName) + 1);
            }
        }

        for (String participantName : participant) {
            // 완주자 명단에 없거나, 완주자 명단에 있었으나 이전 참여자명단에서 모든 완주자가 확인됐을 경우 -> 해당 참여자 이름을 반환
            if (!mapNameToCount.containsKey(participantName) || mapNameToCount.getOrDefault(participantName, 0) < 1) {
                return participantName;
            } else {
                // 참여자 카운팅 맵에서 해당 이름의 잔여인원수를 1 감소시킴
                mapNameToCount.put(participantName, mapNameToCount.get(participantName) - 1);
            }
        }

        return ""; // 컴파일 에러를 막기 위한 마무리코드
    }

    public static void main(String[] args) {
//        String[] participant = new String[]{"leo", "kiki", "eden"};
//        String[] participant = new String[] {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] participant = new String[] {"mislav", "stanko", "mislav", "ana"};
//        String[] completion = new String[]{"eden", "kiki"};
//        String[] completion = new String[] {"josipa", "filipa", "marina", "nikola"};
        String[] completion = new String[] {"stanko", "ana", "mislav"};
        System.out.println(solution(participant, completion));
    }
}
