import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

// 돌면서 이긴애들 리스트를 작성해야함
// 돌면서 방문체크
1 2 3 4 5
1 1 1 1 1

4 = 3 2 5
3 = 2 5
2 = 5
5 = X
1 = 2 5

// 리스트 돌면서 컨테인 카운트
1 = 0
2 = 3
4 = 0
3 = 1
5 = 4

// 이긴 리스트 카운트 + 진 리스트 카운트
// 이게 맴버수-1 이면 확정
// 이런애들 합

2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.
 */
public class Main {

    public static void main(String[] args) {
        int n = 5; // 선수의 수
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}; // 경기결과
        int answer = 0;

        int[] visited = new int[n+1];
        HashMap<Integer, List<Integer>> winnerHashMap = new HashMap<>();
//        winnerHashMap = dfs(4, 0, results, visited, true, winnerHashMap);
//        System.out.println("winnerHashMap : " + winnerHashMap);
//        System.out.println("answer : " + answer);


        for(int i=0; i<results.length; i++){
            /*
            List<Integer> subList;
            if(!winnerHashMap.containsKey(results[i][0])){
                subList = new ArrayList<Integer>();
                winnerHashMap.put(results[i][0], subList);
            }
            subList = winnerHashMap.get(results[i][0]);
            subList.addAll(dfs(results[i][0], results, visited, winnerHashMap));
            */
            dfs(results[i][0], results, visited, winnerHashMap);
        }

        int[] loseCountArray = new int[n+1];
        for(int i=1; i<n+1; i++){
            loseCountArray[i] = loseCountArray[i] + winnerHashMap.get(i).size();
            for(int j=1; j<n+1; j++){
                if(winnerHashMap.get(j).contains(i))
                    loseCountArray[i]++;
            }
        }


        for(int i=0; i<n+1; i++){
            if(loseCountArray[i] == n-1)answer++;
//            System.out.println("loseCountArray[" + i + "] : " + loseCountArray[i] );
        }

        System.out.println("answer : " + answer);


//        System.out.println("list : " + dfs(4, results, visited, winnerHashMap));
//        System.out.println("winnerHashMap : " + winnerHashMap);
    }

    public static List<Integer> dfs(int target, int[][] results, int[] visited,
                           HashMap<Integer, List<Integer>> winnerHashMap) {

//        if(visited[target] == 1) return new ArrayList<>();

        List<Integer> returnList;

        if(!winnerHashMap.containsKey(target)){
            returnList = new ArrayList<Integer>();
            winnerHashMap.put(target, returnList);
        }
        returnList = winnerHashMap.get(target);

        for(int[] result : results){
            // 타겟과 일치하는 노드를 발견
            if(target == result[0]){
                int nextNode = result[1];

                if(visited[result[1]] == 1) {
                    if(returnList.contains(nextNode))
                        continue;
                    else {
                        if(winnerHashMap.containsKey(nextNode)){
                            returnList.addAll(winnerHashMap.get(nextNode));
                            returnList.add(nextNode);
                            continue;
                        }
                    }

                }

                List<Integer> subList = dfs(nextNode, results, visited, winnerHashMap);
                // 리턴할 리스트
                returnList.add(nextNode);
                returnList.addAll(subList);
            }
        }


        visited[target] = 1;
        return returnList;
    }

    public static HashMap<Integer, List<Integer>> dfs(int targetNode, int resultNum, int[][] results, int[] visited, boolean isWinner,
                                                      HashMap<Integer, List<Integer>> winnerHashMap){
//        if(visited[resultNum] == 1)
//            return winnerHashMap;


        System.out.println("targetNode : " + targetNode);
//        System.out.println("resultNum : " + resultNum);
        for (int i=0; i<results.length; i++){
            if(visited[i] == 1) continue;

            if(targetNode == results[i][0]){
                int nextNode = results[i][1];
                System.out.println("nextNode : " + nextNode);
                List<Integer> nodeList;
                if(!winnerHashMap.containsKey(targetNode)){
                    nodeList = new ArrayList<Integer>();
                    winnerHashMap.put(targetNode, nodeList);
                }
                nodeList = winnerHashMap.get(targetNode);
                nodeList.add(results[i][1]);

                // winner
                visited[resultNum] = 1;
                dfs(nextNode, i, results, visited, isWinner, winnerHashMap);
            }
        }
        System.out.println("not find node : " + targetNode);
        return winnerHashMap;
    }

}
