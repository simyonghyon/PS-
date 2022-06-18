//3
class Solution {

    public int[] solution(int n, String[] plans, String[] clients) {
        int[] answer = new int[clients.length];
        Payment[] payments = new Payment[plans.length];

        List<Integer> services = new ArrayList<>();

        for(int i = 0; i < plans.length; i++){
            StringTokenizer st = new StringTokenizer(plans[i], " ");
            int data = Integer.valueOf(st.nextToken());

            while(st.hasMoreTokens()){
                services.add(Integer.valueOf(st.nextToken()));
            }

            payments[i] = new Payment(i + 1, data, services);
        }

        for(int i = 0; i < plans.length; i++){
            System.out.println(payments[i].toString());
        }

        for(int i = 0; i < clients.length; i++){
            StringTokenizer st = new StringTokenizer(clients[i], " ");
            int data = Integer.valueOf(st.nextToken());
            List<Integer> wantService = new ArrayList<>();
            while(st.hasMoreTokens()){
                wantService.add(Integer.valueOf(st.nextToken()));
            }
            int k = 0;
            for(k = 0; k < plans.length; k++){
                if(payments[k].containService(wantService)) break;
            }
            while(k < plans.length){
                if(payments[k].data >= data){
                    answer[i] = payments[k].number;
                    break;
                }
                k++;
            }
            if(k == n) answer[i] = 0;
        }

        return answer;
    }
}

class Payment{
    int number;
    int data;
    Set<Integer> services = new HashSet<>();

    public Payment(int number, int data, List<Integer> additionalService) {
        this.number = number;
        this.data = data;
        for(Integer additional : additionalService){
            this.services.add(additional);
        }
    }

    public String toString(){
        String s = number + " " + data;
        for(Integer i : services){
            s += " " + i;
        }
        return s;
    }

    public boolean containService(List<Integer> wantService){
        for(Integer service : wantService){
            if(!services.contains(service)) return false;
        }

        return true;
    }


}