//https://programmers.co.kr/learn/courses/30/lessons/42579

class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {

        Hashtable<String, Integer> total = new Hashtable<>();
        HashSet<String> set = new HashSet<>();
        Hashtable<String, List<Integer>> music = new Hashtable<>();

        List<Integer> linkedList;
        for(int i = 0; i < genres.length; i++){
            if(total.containsKey(genres[i])){
                total.put(genres[i], total.get(genres[i]) + plays[i]);
                linkedList = music.get(genres[i]);

                int tmp = i;
                if(plays[linkedList.get(0)] < plays[tmp]){
                    tmp = linkedList.get(0);
                    linkedList.set(0, i);
                }

                if(linkedList.size() == 2 && plays[linkedList.get(1)] < plays[tmp]){
                    linkedList.set(1, tmp);
                }
                else if(linkedList.size() == 1){
                    linkedList.add(tmp);
                }

                music.put(genres[i], linkedList);
            }
            else{
                total.put(genres[i], plays[i]);
                linkedList = new LinkedList<>();
                linkedList.add(i);
                music.put(genres[i], linkedList);
            }
            set.add(genres[i]);
        }

        List<pair> list = new LinkedList<>();

        for(var s : set){
            list.add(new pair(s, total.get(s)));
        }

        Collections.sort(list);

        List<Integer> answer = new LinkedList<>();
        for(int i = 0; i < list.size(); i++){
            for(var k : music.get(list.get(i).genre)){
                answer.add(k);
            }
        }
        
        return answer;
    }
}


class pair implements Comparable<pair>{

    String genre;
    int total = 0;

    public pair(String genre, int total){
        this.genre = genre;
        this.total = total;
    }

    @Override
    public int compareTo(pair o) {
        return Integer.compare(o.total, total);
    }
}