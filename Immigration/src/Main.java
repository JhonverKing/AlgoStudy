import java.util.Arrays;

public class Main {
/*
 - 입국자의 수가 최대 10억
 - 심사관의 수가 최대 10만
 - 각 심사관이 걸리는 최대 시간이 10억

 */
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
//        int[] times = {1, 10};

        long answer = 0;
        Arrays.sort(times);

        answer = immigration(n, times);



        System.out.println("answer : " + answer);

    }

    public static long immigration(int n, int[] times){

        long result = 0;
        long left = times[0];
        long right = times[times.length-1] * n;


        while(left <= right){
            long mid = (left + right) / 2;

            System.out.println("left : " + left);
            System.out.println("right : " + right);
            System.out.println("mid : " + mid);

            int cnt = 0;
            for(int time : times){
                cnt = cnt + (int)(mid / time);
            }
            System.out.println("cnt : " + cnt);
            System.out.println("");

            if(cnt < n){
                left = mid + 1;
            } else {
                right = mid - 1;
                result = mid;
            }
        }

        return result;
    }
}
