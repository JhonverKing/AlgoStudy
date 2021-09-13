import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
//        int[] prices = {7,1,5,3,6,4};
//        int[] prices = {1,2,3,4,5};
        int[] prices = {7,6,4,3,1};

        System.out.println("answer : " + process(prices));
    }

    private static int process(int[] prices) {
        int buy = prices[0] * -1;
        int sub = 0;
        int sum = 0;

        for(int i=1; i<prices.length; i++){
            if(sub < buy + prices[i]){
                sub = buy + prices[i];
                if(i == prices.length-1){
                    sum += sub;
                }
            }
            else{
                sum += sub;
                sub = 0;
                buy = prices[i] * -1;
            }
        }
        return sum;
    }
}
