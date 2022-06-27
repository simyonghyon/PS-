import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        CustomHashMap<String, Integer> idToIndex = new CustomHashMap<>(id_list.length * 2);
        //Map<String, Integer> idToIndex = new HashMap<>();
        
        for(int i = 0; i < id_list.length; i++){
            idToIndex.put(id_list[i], i);
            //System.out.println(idToIndex.get(id_list[i]));
        }
        
        boolean[][] reportArray = new boolean[id_list.length][id_list.length];
        int[] reportedCount = new int[id_list.length];
        
        for(int i = 0; i < report.length; i++){
            String s = report[i];
            
            StringTokenizer st = new StringTokenizer(s, " ");
            String user = st.nextToken();
            String reportedUser = st.nextToken();

            if(reportArray[idToIndex.get(reportedUser)][idToIndex.get(user)] == false){
                reportedCount[idToIndex.get(reportedUser)] += 1;
                reportArray[idToIndex.get(reportedUser)][idToIndex.get(user)] = true;
            }
        }
        
        for(int i = 0; i < reportedCount.length; i++){
            if(reportedCount[i] >= k){
                for(int j = 0; j < reportedCount.length; j++){
                    if(reportArray[i][j] == true){
                        answer[j] += 1;
                    }
                }
            }
        }
       
        
        return answer;
    }
}


class CustomHashMap<K, V> {
    private Node[] map;
    private int size;
    private int remainSize;

    public CustomHashMap(int size){
        map = new Node[size];
        this.size = size;
        this.remainSize = size;
        for(int i = 0; i < size; i++){
            map[i] = null;
        }
    }

    public CustomHashMap(){
        this(10);
    }

    public void put(K key, V value){
        int index = (key.hashCode() < 0 ? key.hashCode() * -1 : key.hashCode()) % size;
        Node changeNode = new Node(index, key, value);

        if(map[index] != null){
            Node prev = new Node(-1, key, value);
            Node node = map[index];
            while(node != null && !node.key.equals(key)) {
                prev = node;
                node = node.next;
            }

            prev.next = changeNode;
            if(node != null){
                changeNode.next = node.next;
            }

        } else {
            map[index] = new Node(index, key, value);
        }

        remainSize -= 1;
    }

    public V get(K key){
        int index = (key.hashCode() < 0 ? key.hashCode() * -1 : key.hashCode()) % size;
        Node node = map[index];

        while(node != null) {
            if(node.key.equals(key)){
                return (V)node.value;
            }
            node = node.next;
        }

        return null;
    }

    class Node<K, V>{
        final int hash;
        K key;
        V value;
        Node<K, V> next = null;

        Node(int hash, K key, V value){
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}


// 각 유저는 한번에 한명의 유저를 신고
// - 신고 횟수에 제한은 없다, 다른 유저는 또 신고 가능
// - 한 유저를 여러 번 신고할 수 있지만, 동일한 유저에 대한 신고는 1회만 적용

// k번 이상 신고된 유저는 게시판 정지, 해당 유저 신고한 모든 유저에게 정지 사실 발송
// - 마지막에 한번에 발송
// - 자기가 신고한 유저 중 정지당한애 있으면 알아야 한다

// map으로 id 인덱스 매핑***
// report의 중복을 제거
// 각 유저별 신고당한 횟수
// 이차원 배열 booelan[신고당한 유저][신고한 유저] = 신고여부