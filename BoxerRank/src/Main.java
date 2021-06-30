import java.util.HashMap;
import java.util.HashSet;

/*
n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다.
권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다.
심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다.
하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.

선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때
정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.

선수의 수는 1명 이상 100명 이하입니다.
경기 결과는 1개 이상 4,500개 이하입니다.
results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
모든 경기 결과에는 모순이 없습니다.

n	results	                                    return
5	[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]	2


// 이긴 리스트 카운트 + 진 리스트 카운트
// 이게 맴버수-1 이면 확정
// 이런애들 합

2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.
 */
public class Main {

    public static void main(String[] args) {
        int n = 5; // 선수의 수
//        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}; // 경기결과
        int[][] results = {{3, 5}, {4, 2}, {4, 5}, {5, 1}, {5, 2}}; // 경기결과
        int answer = 0;

        // 기초데이타
        int[] visited = new int[n+1];
        HashMap<Integer, HashSet<Integer>> winnerHashMap = new HashMap<>();
        for(int i=1; i<n+1; i++){
            winnerHashMap.put(i, new HashSet<Integer>());
        }

        // 해쉬맵 데이타 완성
        for(int i=1; i<n+1; i++){
            dfs(i, results, visited, winnerHashMap);
        }

        // 완성된 해쉬맵에서
        // 특정 넘버의 list.size() 와
        // 특정 넘버를 이긴 수를 더하여 카운트
        int[] loseCountArray = new int[n+1];
        for(int i=1; i<n+1; i++){
            loseCountArray[i] = loseCountArray[i] + winnerHashMap.get(i).size();
            for(int a :winnerHashMap.get(i)){
                loseCountArray[a]++;
            }
        }

        // 카운트가 n-1 이면 순위가 확실한 멤버 카운트 +1
        for(int i=1; i<n+1; i++){
            if(loseCountArray[i] == n-1)
                answer++;
//            System.out.println("loseCountArray[" + i + "] : " + loseCountArray[i]);
        }
        System.out.println("answer : " + answer);
        System.out.println("winnerHashMap : " + winnerHashMap);
    }

    public static HashSet<Integer> dfs(int target, int[][] results, int[] visited,
                                    HashMap<Integer, HashSet<Integer>> winnerHashMap) {

        if(visited[target] == 1) return null;
        HashSet<Integer> lowerThanCurrentNode = new HashSet<Integer>();

        for(int[] result : results){
            // 타겟과 일치하는 노드를 발견
            if(target == result[0]){
                int nextNode = result[1];
                HashSet<Integer> lowerThanNextNode;

                // nextNode가 방문완료된 노드면
                // nextNode 보다 낮은 노드 리스트를 가져와서 현재 노드 보다 낮은 노드 리스트에 추가한다.
                if(visited[nextNode] == 1) {
                    lowerThanNextNode = winnerHashMap.get(nextNode);
                }
                // nextNode가 방문완료가 아니면
                // 재귀 실행해서 nextNode 보다 낮은 노드 리스트를 가져와서 현재 노드 보다 낮은 노드 리스트에 추가한다.
                else {
                    lowerThanNextNode = dfs(nextNode, results, visited, winnerHashMap);
                }
                lowerThanCurrentNode.add(nextNode);
                lowerThanCurrentNode.addAll(lowerThanNextNode);
            }
        }

        // 해쉬맵에 현재 노드보다 낮은 노드 리스트를 추가함
        winnerHashMap.get(target).addAll(lowerThanCurrentNode);

        visited[target] = 1;
        return lowerThanCurrentNode;
    }
}
