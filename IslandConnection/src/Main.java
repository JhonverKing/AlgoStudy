import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        int n=4;
//        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}; // 4
        int[][] costs = {{0, 1, 1}, {2, 3, 1}, {1, 2, 5}}; // 7

//        int n=5;
//        int[][] costs = {{0, 1, 5}, {1, 2, 3}, {2, 3, 3}, {3, 1, 2}, {3, 0, 4}, {2, 4, 6}, {4, 0, 7}}; // 15

        System.out.println(process(n, costs));

    }

    public static int process(int n, int[][] costs){
        int sum=0;
        HashMap<Integer, HashSet<Integer>> visitedMap = new HashMap<>();
        for(int i=0; i<n; i++){
            visitedMap.put(i, new HashSet<>());
        }

        while(visitedMap.get(0).size() < n){
            int min = Integer.MAX_VALUE;
            int beginNode = 0;
            int endNode = 0;

            for(int[] cost : costs){
                // 이미 연결된 노드는 넘김
                if(visitedMap.get(cost[0]).contains(cost[1])) continue;

                // 최소비용 구함
                if(min > cost[2]){
                    min = cost[2];
                    beginNode = cost[0];
                    endNode = cost[1];
                }
            }

            // 최소비용인 노드를 찾았다면
            if(min != Integer.MAX_VALUE){
                // 현재 연결할 노드가 가지고 있는 기존 리스트에 현재 연결할 노드들을 추가함
                HashSet<Integer> visitedList = visitedMap.get(beginNode);
                visitedList.addAll(visitedMap.get(endNode));
                visitedList.add(beginNode);
                visitedList.add(endNode);

                // 연결된 노드들이 같은 list를 가지도록 addAll 해줌
                for(int num:visitedList){
                    visitedMap.get(num).addAll(visitedList);
                }
                sum += min;
            }
        }
        return sum;
    }

}
