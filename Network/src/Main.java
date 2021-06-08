
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    /*
    문제 설명
        네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고,
        컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다.
        따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.
        컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

    제한사항
        컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
        각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
        i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
        computer[i][i]는 항상 1입니다.

    입출력 예
        n   computers                           return
        3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]	2
        3	[[1, 1, 0], [1, 1, 1], [0, 1, 1]]	1
     */

    public static void main(String[] args) {
//        int[][] computers = {{1, 0, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 1}, {1, 0, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}};
        int[][] computers = {{1, 0, 0, 1}, {0, 1, 1, 1}, {0, 1, 1, 0}, {1, 1, 0, 1}};
        int answer = 0;
        int[] visitedArray = new int[computers.length];

        int[][] comsClone = new int[computers.length][computers.length];
        for(int i=0; i<computers.length; i++)
            comsClone[i] = computers[i].clone();


        for (int i = 0; i < comsClone.length; i++) {
//            if(isLinked(comsClone, i)) answer++;
            if(isLinked(computers, i, visitedArray)) answer ++;
        }

        for(int i=0; i< computers.length; i++){
            System.out.println("array : " + computers[i][i]);
        }
        System.out.println("answer : " + answer);
    }

    public static boolean isLinked(int[][] coms, int depth, int[] visited) {

        if (visited[depth] == 1)
            return false;

        visited[depth] = 1;
        for (int i = 0; i < coms[depth].length; i++) {
            if (depth == i) continue;

            if (coms[depth][i] == 1)
                isLinked(coms, i, visited);
        }

        return true;
    }

    // 메서드 내부에서 coms[depth][depth] == 2 이렇게 비교하는게 직관적이지 않고
    // computers의 값을 변경했기 때문에 다시 활용할수 없음,
    // computers를 원본으로 유지하려면 결국 2차원 배열에 대한 Deep Copy를 해야함
    public static boolean isLinked(int[][] coms, int depth) {

        if (coms[depth][depth] == 2)
            return false;

        coms[depth][depth] = 2;
        for (int i = 0; i < coms[depth].length; i++) {
            if (depth == i) continue;

            if (coms[depth][i] == 1)
                isLinked(coms, i);
        }

        return true;
    }


    //[[1, 1, 0], [1, 1, 0], [0, 0, 1]]
    //[[1, 1, 0, 0], [1, 1, 0, 1], [0, 0, 1, 0], [0, 1, 0, 1]]
    public static boolean BFS(int[][] coms, int depth){
        int[] visited = new int[coms.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();


//        while(queue.size() != 0){
//
//            for()
//
//        }


        for(int i=0; i<coms.length; i++){
            if(visited[i] == 0)
            {
                visited = new int[coms.length];
            }
            for(int j=0; j<coms[i].length; j++){
                if(coms[i][j] == 1){
                    visited[j] = 1;
                }
            }
        }
        return false;
    }

}
