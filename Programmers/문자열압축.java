class Solution {
    public int solution(String s) {
        int answer = 0;
        
        return getMinLength(s);
    }
    
    private int getMinLength(String s){
        int min = s.length();
        
        for(int i = 1; i <= s.length() / 2; i++){
            int length = calcMinLengthByN(s, i);
            //System.out.println(length);
            min = Math.min(length, min);
        }
        
        return min;
    }
    
    private int calcMinLengthByN(String s, int n){
        int index = 0;
        StringBuilder sb = new StringBuilder("");
        
        while(index + n <= s.length()){
            String unit = s.substring(index, index + n);
            //System.out.println(unit);
            int k = index + n;
            int count = 1;
            
            while(k + n <= s.length()){
                if(s.substring(k, k + n).equals(unit)){
                    //System.out.println("sub " + s.substring(k, k + n));
                    count++;
                    k = k + n;
                
                } else {
                    break;
                }
            }
            
            if(count > 1){
                sb.append(count + unit);
            
            } else {
                sb.append(unit);
            }
            
            index = k;
        }
        
        if(index < s.length()){
            sb.append(s.substring(index, s.length()));
        }
        
        return sb.length();
    }
}