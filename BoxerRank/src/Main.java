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
//        int[][] results = {{3, 5}, {4, 2}, {4, 5}, {5, 1}, {5, 2}}; // 경기결과
        int[][] results = {{1, 4}, {4, 2}, {2, 5}, {5, 3}};
        int answer = 0;

        graph(n, results);
    }
    public static int graph2(int n, int[][] results){
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
            // System.out.println("loseCountArray[" + i + "] : " + loseCountArray[i]);
        }
        System.out.println("answer : " + answer);
        System.out.println("winnerHashMap : " + winnerHashMap);

        return answer;
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
                // nextNode가 방문완료가 아니면
                // 재귀 실행해서 nextNode 보다 낮은 노드 리스트를 가져와서 현재 노드 보다 낮은 노드 리스트에 추가한다.
                if(visited[nextNode] == 1) {
                    lowerThanNextNode = winnerHashMap.get(nextNode);
                }
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

    public static int graph(int n, int[][] results){

        int[][] graph = new int[n+1][n+1];
        for(int i=0; i< results.length; i++){
            graph[results[i][0]][results[i][1]] = 1;
        }

        // 가로로 트루 찾다가 발견시
        // grahph[j][i] == 1
        // => row에 첫번째 셀부터 마지막쌜까지 1인걸 찾는다
        // graph[i][k] == 1
        // => 발견한 row가 i 컬럼에서 1인걸 찾아서 앞에서 찾았던 컬럼에다가 넣음
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                for(int k=1; k<n+1; k++){
                    if(j!=k && graph[j][i] == 1 && graph[i][k] == 1) {
                        graph[j][k] = 1;
                        // System.out.println("[" + j + "][" + i + "], [" + i + "][" + k + "]");
                        // System.out.println("[" + j + "][" + k + "] = " + graph[j][k]);
                    }
                }
            }
        }

        int[] cnt = new int[n+1];
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(graph[i][j] == 1) cnt[i]++;
                if(graph[j][i] == 1) cnt[i]++;
            }
        }

        int answer = 0;
        for(int i=1; i<n+1; i++) {
            System.out.println("cnt[" + i + "] : " + cnt[i]);
            if(cnt[i] == n-1)
                answer++;
        }

        System.out.println("answer : " + answer);
        return answer;
    }
}
