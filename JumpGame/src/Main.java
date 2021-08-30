import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        int[] nums = {2,3,1,1,4};
        int[] nums = {3, 2, 1, 0, 4};
        
//        int[] nums = {0};
        boolean answer = canComplete2(nums);
        System.out.println("answer : " + answer);


    }

    private static boolean canComplete(int[] nums){
        int len = nums.length;

        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> que = new LinkedList<>();
        set.add(0);
        que.add(0);

        while(!que.isEmpty()){
            int curPosition = que.poll();

            for(int i=0; i<=nums[curPosition]; i++) {
                int sum = curPosition + i;
                if(sum >= len-1)
                    return true;
                if(set.contains(sum))
                    continue;
                set.add(sum);
                que.add(sum);
            }
        }

        return false;
    }

    private static boolean canComplete2(int[] nums){
        int max = nums[0];

        for(int i=0; i<=max; i++){
            max = Math.max(max, i + nums[i]);
            if(max >= nums.length-1) return true;
        }

        return false;
    }

}
