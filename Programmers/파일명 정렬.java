//https://programmers.co.kr/learn/courses/30/lessons/17686

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        File[] filesF = new File[files.length];
    
        for(int i = 0; i < files.length; i++){
            filesF[i] = new File(files[i], i);
        }
        
        Arrays.sort(filesF);
        
        for(int i = 0; i < files.length; i++){
            answer[i] = filesF[i].toString();
        }
        return answer;
    }
}

class File implements Comparable<File>{
    String head;
    String number;
    String tail = "";
    int index;
    
    public File(String file, int index){
        this.index = index;
        
        int i = 0;
        for(; i < file.length(); i++){
            if(file.charAt(i) <= '9' && file.charAt(i) >= '0') break;
        }
        this.head = file.substring(0, i);
        
        int k = i;
        for(; i < file.length(); i++){
            if(!(file.charAt(i) <= '9' && file.charAt(i) >= '0')) break;
        }
        this.number = file.substring(k, i);
        
        if(i < file.length()) this.tail = file.substring(i);
    }
    
    @Override
    public int compareTo(File f){
        String head = this.head.toUpperCase();
        String fHead = f.head.toUpperCase();
        
        if(head.compareTo(fHead) == 0){
            if(Integer.valueOf(number).compareTo(Integer.valueOf(f.number)) == 0)
                return Integer.compare(index, f.index);
            return Integer.valueOf(number).compareTo(Integer.valueOf(f.number));
        }
        return head.compareTo(fHead);
    }
    
    @Override
    public String toString(){
        return head + number + tail;
    }
}