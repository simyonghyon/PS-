//https://programmers.co.kr/learn/courses/30/lessons/42889

import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        Stage[] stage = new Stage[N];
        
        for(int i = 0; i < N; i++){
            stage[i] = new Stage(i);
        }
        
        for(int i = 0; i < stages.length; i++){
            for(int k = 1; k < stages[i]; k++){
                stage[k - 1].incleaseArrive();
                stage[k - 1].incleaseCha();
            }
            
            if(stages[i] <= N) stage[stages[i] - 1].incleaseArrive();
        }
        
        Arrays.sort(stage);
        
        for(int i = 0; i < N; i++){
            answer[i] = stage[i].getIndex() + 1;
        }
        
        return answer;
    }
}

class Stage implements Comparable<Stage>{
    private int arrive = 0;
    private int challenge = 0;
    private int index;
    
    public Stage(int index){
        this.index = index;
    }
    
    public int getIndex(){
        return index;
    }
    
    public double getFail(){
        return (double)challenge / (double)arrive;
    }
    
    public void incleaseArrive(){
        this.arrive++;
    }
    
    public void incleaseCha(){
        this.challenge++;
    }
    
    @Override
    public int compareTo(Stage stage){
        return Double.compare(this.getFail(), stage.getFail());
    }
}