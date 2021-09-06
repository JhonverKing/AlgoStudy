import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int n = 12;
        int answer = process(n);
        System.out.println("answer : " + answer);

    }

    public static int process(int n ){

        HashSet<Integer> perfectSquareList = new HashSet<>();
        Queue<Integer> que = new LinkedList<>();
        int cnt = 0;
        que.add(0);

        while(!que.isEmpty()){
            cnt++;

            int curQueSize = que.size();
            for(int i=0; i<curQueSize; i++){
                int curValue = que.poll();
                for(int j = 1; j*j<=n-curValue; j++){
                    int sum = curValue + j*j;

                    if(perfectSquareList.contains(sum))
                        continue;

                    if(sum == n)
                        return cnt;
                    else{
                        que.add(sum);
                        perfectSquareList.add(sum);

                    }
                }
            }

        }

        return -1;
    }

}
