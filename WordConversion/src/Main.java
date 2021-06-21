import java.util.*;

public class Main {

    /*
    두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

    1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
    2. words에 있는 단어로만 변환할 수 있습니다.
    예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면
    "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

    두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때,
    최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
         - 각 단어는 알파벳 소문자로만 이루어져 있습니다.
         - 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
         - words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
         - begin과 target은 같지 않습니다.
         - 변환할 수 없는 경우에는 0를 return 합니다.
     */

    public static void main(String[] args) {
        int answer = 0;
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        int[] visited = new int[words.length];
        answer = dfs(begin, target, words,0, visited);
        System.out.println("dfs : " + answer);
        System.out.println("bfs : " + bfs(begin, target, words));
    }

    // 변경 가능한지 체크
    public static boolean canChange(String begin, String word){
        int cnt = 0;

        for(int j=0; j<begin.length(); j++){
            if(begin.charAt(j) == word.charAt(j)) cnt++;
        }
        if(cnt == begin.length()-1)
            return true;
        return false;
    }

    public static int dfs(String begin, String target, String[] words, int depth, int[] visited) {
        int result = 0;
        // 방문배열을 노드별로 관리하기 위해 새로운 배열을 생성하여 초기값을 클론으로 할당
        int[] visitedClone = visited.clone();


        // 목표 단어와 일치할 경우 노드의 깊이를 반환한다.
        if(begin.equals(target))
            return depth;

        for(int i=0; i<words.length; i++){
            // words[i]가 이미 방문한 노드면 컨티뉴
            if(visitedClone[i] == 1) continue;

            // 변경 가능한 단어는 방문표시 하고 방문
            if(canChange(begin, words[i])) {
                visitedClone[i] = 1;
                int temp = dfs(words[i], target, words, depth+1, visitedClone);

                // 방문한 노드에서 목표단어 까지의 depth를 리턴받아서 가장 작은 값을 result에 담는다
                if(temp != 0){
                    if(result == 0) result = temp;
                    else if(result > temp) result = temp;
                }
            }
        }

        return result;
    }

    public static int bfs(String begin, String target, String[] words) {
        int depth = 0;
        Queue<String> que = new LinkedList<>();
        Queue<String> nextQueue = new LinkedList<>();
        int[] visited = new int[words.length];
        que.add(begin);
//String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        while(que.size()!=0){
            String current = que.poll();
            for(int i=0; i<words.length; i++) {
                // words[i]가 이미 방문한 노드면 컨티뉴
                if(visited[i] == 1) continue;

                // 변경 가능한 단어는 방문표시 하고 다음 방문할 큐에 추가
                if(canChange(current, words[i])) {
                    // 변경 가능한 단어중 target이 있다면 depth+1을 결과로 리턴
                    if(target.equals(words[i])) return depth+1;
                    visited[i] = 1;
                    nextQueue.add(words[i]);
                }
            }

            // 검사할 큐가 비어있을때 다음 방문할 큐가 있다면 방문할 큐를 검사할 큐에 넣고 depth를 1 증가한다.
            // 다음 방문할 큐는 다시 초기화 해준다.
            if(que.size() == 0) {
                if(nextQueue.size() > 0){
                    System.out.println("que : " + nextQueue);
                    que = nextQueue;
                    nextQueue = new LinkedList<>();
                    depth++;
                }
            }
        }

        return 0;
    }
}
