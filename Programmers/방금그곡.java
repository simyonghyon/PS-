class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int ansTotalPlayTime = 0;
        m = replace(m);
        
        for(int i = 0; i < musicinfos.length; i++){
            String[] ss = musicinfos[i].split(",");
            ss[3] = replace(ss[3]);

            int length = ss[3].length();
            int totalPlayTime = getPlayTime(ss[0], ss[1]);
            
            StringBuilder sb = new StringBuilder("");
            for(int k = 0; k < totalPlayTime / length; k++){
                sb.append(ss[3]);
            }

            if(totalPlayTime % length != 0){
                sb.append(ss[3].substring(0, totalPlayTime % length));
            }
            
            String playedMusic = sb.toString();
            if(playedMusic.contains(m)){
                if(ansTotalPlayTime < totalPlayTime){
                    answer = ss[2];
                    ansTotalPlayTime = totalPlayTime;
                }
            }
        }
        
        return answer;
    }
    
    private int getPlayTime(String startTime, String endTime){
        int hour = Integer.valueOf(endTime.substring(0, 2)) - Integer.valueOf(startTime.substring(0, 2));
        int minute = Integer.valueOf(endTime.substring(3, 5)) - Integer.valueOf(startTime.substring(3, 5));
        
        return hour * 60 + minute;
    }
    
    private String replace(String s){
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '#'){
                sb.delete(sb.length() - 1, sb.length());
                sb.append((char)(s.charAt(i - 1) + 8));
            
            } else {
                sb.append(s.charAt(i));
            }
        }
        
        return sb.toString();
    }
}