import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        Arrays.stream(course)
                .forEach(i->{
                    CombinationProcess process = new CombinationProcess(i);
                    answer.addAll(process.process(
                            Arrays.stream(orders)
                                    .map(Order::new)
                                    .collect(Collectors.toList())));
                });

        return answer.stream()
                .sorted()
                .toArray(String[]::new);
    }
}

class CombinationProcess {

    private final Map<String, Integer> orderCombination;
    private final int courseLength;

    public CombinationProcess(int courseLength) {
        this.orderCombination = new HashMap<>();
        this.courseLength = courseLength;
    }

    public List<String> process(List<Order> orders) {
        orders
                .forEach(order -> {
                    for (String combination : order.getAllCombination(courseLength)) {
                        orderCombination.computeIfPresent(combination, (k, v) -> v + 1);
                        orderCombination.putIfAbsent(combination, 1);
                    }
                });
        if (orderCombination.isEmpty()) {
            return new ArrayList<>();
        }

        Integer maxCombinationCount = Collections.max(orderCombination.values());
        if (maxCombinationCount < 2) {
            return new ArrayList<>();
        }

        return orderCombination.keySet()
                .stream()
                .filter(key->orderCombination.get(key).equals(maxCombinationCount))
                .distinct()
                .collect(Collectors.toList());
    }
}

class Order {
    private final char[] order;

    public Order(String order) {
        char[] orderArray = order.toCharArray();
        Arrays.sort(orderArray);
        this.order = orderArray;
    }

    public List<String> getAllCombination(final int course) {
        return calcAllCombination(course, 0, new StringBuilder(""), new ArrayList<>());
    }

    private List<String> calcAllCombination(final int course, int index, StringBuilder orderCombination, List<String> allCombination) {
        if (orderCombination.length() >= course) {
            allCombination.add(orderCombination.toString());
            return allCombination;

        } else if (index >= order.length) {
            return allCombination;
        }

        for (int i = index; i < order.length; i++) {
            orderCombination.append(order[i]);
            calcAllCombination(course, i + 1, orderCombination, allCombination);
            orderCombination.deleteCharAt(orderCombination.length() - 1);
        }

        return allCombination;
    }
}

// 가장 많이 함께 주문한 단품 메뉴들을 코스요리
// 최소 2가지, 최소 2명 이상의 손님으로터 주문된 메뉴조합