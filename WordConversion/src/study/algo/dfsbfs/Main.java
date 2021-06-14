package study.algo.dfsbfs;

import java.util.ArrayList;
import java.util.List;

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
    static int result = 0;

    public static void main(String[] args) {

        int answer = 0;
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
//        String[] words = {"hhh","hht"};
        List resultList = new ArrayList();

        int[] visited = new int[words.length];
        dfs(begin, target, words,0, visited, -1, result);

        if(resultList.size() != 0) answer = (int)resultList.get(0);
        for(Object i : resultList){
            if(answer > (int)i) answer = (int)i;
        }

        System.out.println("answer : " + answer);

	// write your code here
    }
    public static void dfs(String begin, String target, String[] words, int sum, int[] visited, int depth, List resultList) {
        int[] visited2 = visited.clone();

        if(depth >= 0){
            if(visited2[depth] == 1) return;
            visited2[depth] = 1;
        }

        if(begin.equals(target)) {
            resultList.add(sum);
            return;
        }

        for(int j=0; j<words.length; j++){
            int cnt = 0;
            for(int i=0; i<begin.length(); i++){
                if(begin.substring(i, i+1).equals(words[j].substring(i, i+1))) cnt++;
            }
            if(cnt == 2) {
                dfs(words[j], target, words, sum + 1, visited2, j, resultList);
            }
        }
        return;
    }

    public static void dfs(String begin, String target, String[] words, int sum, int[] visited, int depth, int result) {
        int[] visited2 = visited.clone();

        if(depth >= 0){
            if(visited2[depth] == 1) return;
            visited2[depth] = 1;
        }

        if(begin.equals(target)) {
            if(sum != 0 && result < sum)
                sum = result;
            return;
        }

        for(int j=0; j<words.length; j++){
            int cnt = 0;
            for(int i=0; i<begin.length(); i++){
                if(begin.substring(i, i+1).equals(words[j].substring(i, i+1))) cnt++;
            }
            if(cnt == 2) {
                dfs(words[j], target, words, sum + 1, visited2, j, result);
            }
        }
        return;
    }

}
