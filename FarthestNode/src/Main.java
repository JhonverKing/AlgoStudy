import java.util.LinkedList;
import java.util.Queue;

public class Main {
/*
n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다.
1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.

n	vertex	                                                    return
6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	3
 */
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int answer = 0;


        answer = getaa(n, edge);
        System.out.println("answer : " + answer);
    }

    public static int getaa(int n, int[][] edge){

        Queue<Integer> curQue = new LinkedList<>();
        Queue<Integer> nextQue = new LinkedList<>();
        int nodeCnt = 1;

        int[] visitedEdge = new int[edge.length];
        int depth = 0;

        curQue.add(1);
        while(curQue.size()!=0){
            int curNode = curQue.poll();

            for(int j=0; j<edge.length; j++){
                if(visitedEdge[j] == 1) continue;

                if(edge[j][0] == curNode || edge[j][1] == curNode){
//                    System.out.println("edge : " + edge[j][0] + ", " + edge[j][1]);
                    for (int childNode:edge[j]) {
                        visitedEdge[j] = 1;

                        // 자식노드가 현재노드와 같지않음 (중복 및 무한반복 제거를 위함)
                        // 자식노드가 현재큐에 없음 (중복 및 무한반복 제거를 위함)
                        // 자식노드가 다음큐에 없음 (중복 및 무한반복 제거를 위함)
                        // ==> 자식노드를 다음 큐에 추가한다.
                        if(childNode != curNode
                                && !curQue.contains(childNode)
                                && !nextQue.contains(childNode))
                        {
                            nextQue.add(childNode);
                            nodeCnt++;
                        }
                    }
                }
            }
            if(curQue.size() == 0) {
                if(nextQue.size() > 0){
//                    System.out.println("nextQue : " + nextQue);
                    curQue = nextQue;
                    nextQue = new LinkedList<>();
                    depth++;

                    // nodeCnt와 노드크기가 일치하면 종료
                    if(nodeCnt == n)
                        return curQue.size();
                }
            }
        }
    return 0;
    }

}
