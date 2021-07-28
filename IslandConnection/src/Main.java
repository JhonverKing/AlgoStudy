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
        // init
        HashMap<Integer, HashSet<Integer>> connectedLists = new HashMap<>();
        // key값을 미리 셋팅함
        for(int i=0; i<n; i++){
            connectedLists.put(i, new HashSet<>());
        }
        int sum=0;

        // 대충 아무 인덱스나 확인해도 연결되어 있다면 같은 값을 뱉지만
        // 섬의 개수 n은 1 이상 100 이하입니다. 제한사항이 있으므로 get(0)으로 고정
        while(connectedLists.get(0).size() < n){
            // 최소 비용인 노드들을 찾을거임
            int min = Integer.MAX_VALUE;
            int beginNode = 0;
            int endNode = 0;

            for(int[] cost : costs){
                // 이미 연결된 노드는 넘김
                if(connectedLists.get(cost[0]).contains(cost[1])) continue;

                // 최소비용 구함
                if(min > cost[2]){
                    min = cost[2];
                    beginNode = cost[0];
                    endNode = cost[1];
                }
            }

            // 최소비용인 노드들을 찾았다면
            if(min != Integer.MAX_VALUE){
                // 노드들이 가지고 있는 지금까지 연결된 노드 리스트를 합침
                connectedLists.get(beginNode).addAll(connectedLists.get(endNode));
                HashSet<Integer> curList = connectedLists.get(beginNode);

                // 합친거에 위에서 찾은 최소비용인 노드들도 추가
                curList.add(beginNode);
                curList.add(endNode);

                // 연결된 노드들이 같은 list를 가지도록 addAll 해줌
                for(int nodeNum : curList){
                    connectedLists.get(nodeNum).addAll(curList);
                }
                sum += min;
            }
        }
        return sum;
    }

}
