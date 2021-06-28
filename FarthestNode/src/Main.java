import java.util.*;

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

//        answer = getFarthestNodeCount(n, edge);
        int[] visitedNode = new int[n+1];
        int[] distanceToNode = new int[n+1];
        HashMap<Integer, List<Integer>> adjNodeList = getAdjNodeList(edge);
        visitedNode[1] = 1;
        answer = dfs(1, visitedNode, 0, adjNodeList, distanceToNode);
        System.out.println("answer : " + answer);
    }
    // 1 : 3, 2
    // 1=[3, 2],
    // 2=[3, 1, 4, 5],
    // 3=[6, 4, 2, 1],
    // 4=[3, 2],
    // 5=[2],
    // 6=[3]

    public static int dfs(int curNode, int[] visitedEdge, int depth, HashMap<Integer, List<Integer>> adjNodeList, int[] distanceToNode){
        System.out.println("curNode : " + curNode);
        for(int adjNode : adjNodeList.get(curNode)){
            int[] cloneVisitedEdge = visitedEdge.clone();
            if(cloneVisitedEdge[adjNode] == 1) continue;

            cloneVisitedEdge[adjNode] = 1;
            int temp = dfs(adjNode, cloneVisitedEdge, depth+1, adjNodeList, distanceToNode);
            if(distanceToNode[curNode] == 0 || distanceToNode[curNode] > temp)
                distanceToNode[curNode] = temp;
            if(distanceToNode[adjNode] == 0)
                distanceToNode[adjNode] = depth + 1;
            if(distanceToNode[adjNode] > temp)
                distanceToNode[adjNode] = temp;
        }

        if(curNode == 1 && depth == 0){
            int max = 0;
            int cnt = 0;
            for(int i=0; i< distanceToNode.length; i++){
                if(max < distanceToNode[i]) {
                    max = distanceToNode[i];
                    cnt = 1;
                }
                else if(max == distanceToNode[i])
                    cnt++;
            }
            return cnt;
        }
        return depth;
    }

    public static int getFarthestNodeCount(int n, int[][] edge){

        Queue<Integer> curQue = new LinkedList<>();
        Queue<Integer> nextQue = new LinkedList<>();
        int[] visitedEdge = new int[edge.length];
        int nodeCnt = 1;
        int depth = 0;
        int cnt = 0;

        // 시작노드
        curQue.add(1);
        while(curQue.size()!=0){
            int curNode = curQue.poll();

            for(int i=0; i<edge.length; i++){
                cnt++;
                if(visitedEdge[i] == 1)
                    continue;

                if(edge[i][0] == curNode || edge[i][1] == curNode){
                    System.out.println("edge : " + edge[i][0] + ", " + edge[i][1]);
                    for (int childNode:edge[i]) {
                        visitedEdge[i] = 1;

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
                    System.out.println("nextQue : " + nextQue);
                    curQue = nextQue;
                    nextQue = new LinkedList<>();
                    depth++;

                    // nodeCnt와 노드크기가 일치하면 종료
                    if(nodeCnt == n){
                        System.out.println("cnt : " + cnt);
                        return curQue.size();
                    }
                }
            }
        }
        return 0;
    }

    public static int getFarthestNodeCount2(int n, int[][] edge){
        Queue<Integer> curQue = new LinkedList<>();
        Queue<Integer> nextQue = new LinkedList<>();
        int[] visitedEdge = new int[n+1];
        int nodeCnt = 1;
        int depth = 0;

        // 시작노드
        curQue.add(1);
        visitedEdge[1] = 1;
        while(curQue.size()!=0){
            int curNode = curQue.poll();

            for(int i=0; i<edge.length; i++){
                for(int j=0; j<2; j++){

                    if(edge[i][j] == curNode){
                        int childNode = 0;

                        if(j==0) childNode = edge[i][1];
                        else childNode = edge[i][0];

                        if(visitedEdge[childNode] == 1)
                            continue;
                        System.out.println("edge : " + edge[i][0] + ", " + edge[i][1]);
                        nextQue.add(childNode);
                        nodeCnt++;
                        visitedEdge[childNode] = 1;
                    }
                }
            }
            if(curQue.size() == 0) {
                if(nextQue.size() > 0){
                    System.out.println("nextQue : " + nextQue);
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

    public static int getFarthestNodeCount3(int n, int[][] edge){
        HashMap<Integer, List<Integer>> adjNodeList = getAdjNodeList(edge);
        Queue<Integer> curQue = new LinkedList<>();
        Queue<Integer> nextQue = new LinkedList<>();
        int[] visitedEdge = new int[n+1];
        int nodeCnt = 1;
        int depth = 0;
        int cnt = 0;

        curQue.add(1);
        visitedEdge[1] = 1;

        while(!curQue.isEmpty()){
            int curNode = curQue.poll();

            System.out.println("adjNode : " + adjNodeList.get(curNode));
            for(int adjNode : adjNodeList.get(curNode)){
                cnt++;
                if(visitedEdge[adjNode] == 1) continue;

                visitedEdge[adjNode] = 1;
                nextQue.add(adjNode);
                nodeCnt++;
            }

            if(curQue.isEmpty()) {
                if(!nextQue.isEmpty()){
                    System.out.println("nextQue : " + nextQue);
                    curQue = nextQue;
                    nextQue = new LinkedList<>();
                    depth++;

                    // nodeCnt와 노드크기가 일치하면 종료
                    if(nodeCnt == n){
                        System.out.println("cnt : " + cnt);
                        return curQue.size();
                    }
                }
            }
        }


        return 0;
    }


    public static HashMap<Integer, List<Integer>> getAdjNodeList(int[][] edge){
        HashMap<Integer, List<Integer>> adjNodeList = new HashMap<Integer, List<Integer>>();

        for(int[] node:edge){
            List<Integer> nodeList;

            for(int i=0; i<2; i++){
                int keyNode = node[i];
                int valueNode = node[i==0 ? 1 : 0];

                if(!adjNodeList.containsKey(keyNode)){
                    nodeList = new ArrayList<Integer>();
                    adjNodeList.put(keyNode, nodeList);
                }

                nodeList = adjNodeList.get(keyNode);
                nodeList.add(valueNode);
            }
        }

        System.out.println("adjNodeList : " + adjNodeList);

        return adjNodeList;
    }
}
