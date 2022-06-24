//https://programmers.co.kr/learn/courses/30/lessons/92341s
import java.util.*;
import java.util.stream.Collectors;



class Solution {

    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> accumulateFee = new HashMap<>();
        HashMap<String, ArrayList<CarParking>> carMap = new HashMap<>();

        Fee fee = new Fee(fees);

        for(int i = 0; i < records.length; i++){
            StringTokenizer st = new StringTokenizer(records[i], " ");
            String time = st.nextToken();
            String carNum = st.nextToken();
            String inOut = st.nextToken();

            if(inOut.equals("IN")){
                CarParking cp = new CarParking(carNum, time);
                if(!carMap.containsKey(carNum)){
                    carMap.put(carNum, new ArrayList<>());
                }
                carMap.get(carNum).add(cp);

            } else{
                ArrayList<CarParking> list = carMap.get(carNum);
                list.get(list.size() - 1).setOutTime(time);
                list.get(list.size() - 1).getOutTime();
            }
        }

        for(String carNum : carMap.keySet()){
            List<CarParking> parkingList = carMap.get(carNum);

            int sum = 0;
            for(CarParking cp : parkingList){
                sum += cp.parkingTime();
            }
            accumulateFee.put(carNum, fee.calcFee(sum));
        }

        List<Integer> list = accumulateFee.keySet().stream()
                .sorted()
                .map(key->accumulateFee.get(key))
                .collect(Collectors.toList());

        int[] answer = new int[list.size()];

        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }

        return answer;
    }
}

class Fee{
    private int basicTime;
    private int basicFee;
    private int unitTime;
    private int unitFee;

    public Fee(int[] fees){
        this.basicTime = fees[0];
        this.basicFee = fees[1];
        this.unitTime = fees[2];
        this.unitFee = fees[3];
    }

    public int calcFee(int parkingTime){
        int fee = 0;
        fee += basicFee;

        // 기본 시간 초과
        if(parkingTime > basicTime){
            int unit = (parkingTime - basicTime) / unitTime;
            if((parkingTime - basicTime) % unitTime != 0){
                unit += 1;
            }
            fee += unit * unitFee;
        }
        return fee;
    }

    private int calcTimeToMinute(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        int inputHour = Integer.valueOf(st.nextToken());
        int inputMinute = Integer.valueOf(st.nextToken());
        return inputHour * 60 + inputMinute;
    }
}

class CarParking{
    private String inputTime;
    private String outTime;
    private String carNum;

    public CarParking(String carNum, String inputTime){
        this.carNum = carNum;
        this.inputTime = inputTime;
        this.outTime = "23:59";
    }

    public int parkingTime(){
        return calcTimeToMinute(outTime) - calcTimeToMinute(inputTime);
    }

    private int calcTimeToMinute(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        int inputHour = Integer.valueOf(st.nextToken());
        int inputMinute = Integer.valueOf(st.nextToken());
        return inputHour * 60 + inputMinute;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}

// 기본요금
// 올림
// 기본시간 기본요금 단위시간 단위요금
// 시간 번호 인아웃
// 차량 번호가 작은 자동차부터 주차 요금
// 나갔다 다시 들어올 수 있다