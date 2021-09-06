import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        int n = 4;
        int[] works = {2,10,10};
        long answer = process2(n, works);
        System.out.println("answer : " + answer);

	// write your code here
    }


    private static long process(int n, int[] works){

        PriorityQueue<Integer> overtime = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            overtime.offer(work);
        }

        for (int i = 0; i < n; i++) {
            int max = (int)overtime.poll();
            if (max <= 0) break;
            overtime.offer(max - 1);
        }

        return sumPow(overtime);

    }

    private static long sumPow(PriorityQueue<Integer> works) {
        long sum = 0;
        while (!works.isEmpty()) {
            sum += Math.pow(works.poll(), 2);
        }
        return sum;
    }


    private static long process2(int n, int[] works){
        int[] worksClone = new int[works.length];
        int sum = getSum(works) - n;
        if(sum <= 0) return 0;

        int avg = sum/works.length;

        for(int i=0; i<works.length; i++){
            if(works[i] < avg) {
                worksClone[i] = works[i];
            }
            else{
                worksClone[i] = avg;
            }
            sum -= worksClone[i];
        }

        for(int i=0; i<works.length; i++){
            if(sum <= 0) break;
            if(works[i] > worksClone[i]){
                worksClone[i]++;
                sum--;
            }
            if(i==works.length-1) i = -1;
        }

        return getResult(worksClone);
    }

    private static long getResult(int[] works){
        long sum = 0;
        for(int work : works){
            sum += work * work;
        }
        return sum;
    }

    private static int getSum(int[] works){
        int sum = 0;
        for(int work : works){
            sum += work;
        }
        return sum;
    }


}
