//https://programmers.co.kr/learn/courses/30/lessons/17676

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        Traffic[] traffics = new Traffic[lines.length];

        for (int i = 0; i < lines.length; i++) {
            traffics[i] = new Traffic(lines[i]);
        }

        Arrays.sort(traffics);

        for (int i = 0; i < traffics.length; i++) {

            LocalDateTime chuseok = traffics[i].startLocalDateTime;
                int count = 0;

                for (int k = 0; k < traffics.length; k++) {
                    if (traffics[k].startLocalDateTime.isAfter(chuseok.plusSeconds(1))) break;
                    if (traffics[k].isInclude(chuseok, chuseok.plusSeconds(1).minusNanos(1000000))) {
                        count++;
                    }
                }

                answer = Math.max(count, answer);
            
                chuseok = traffics[i].endLocalDateTime;
                count = 0;

                for (int k = 0; k < traffics.length; k++) {
                    if (traffics[k].startLocalDateTime.isAfter(chuseok.plusSeconds(1))) break;
                    if (traffics[k].isInclude(chuseok, chuseok.plusSeconds(1).minusNanos(1000000))) {
                        count++;
                    }
                }

                answer = Math.max(count, answer);
        }
        return answer;
    }
}

class Traffic implements Comparable<Traffic>{
    LocalDateTime endLocalDateTime;
    LocalDateTime startLocalDateTime;
    Long handleTime;

    public Traffic(String traffic){
        traffic.replace("s", "");

        String[] strings = traffic.split(" ");

        strings[2] = strings[2].replace("s", "");
        this.handleTime = (long)((Double.valueOf(strings[2]) - 0.001) * 1000000000);
        this.endLocalDateTime = LocalDateTime.parse(strings[0] + " " + strings[1],
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        this.startLocalDateTime = endLocalDateTime.minusNanos(handleTime);
    }

    public boolean isInclude(LocalDateTime start, LocalDateTime end){
        return !(startLocalDateTime.isAfter(end) || endLocalDateTime.isBefore(start));
    }

    @Override
    public int compareTo(Traffic traffic){
        return startLocalDateTime.compareTo(traffic.startLocalDateTime);
    }
}