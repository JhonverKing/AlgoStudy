import java.util.*;

public class Main {

    public static void main(String[] args) {
//        int[] nums = {10,9,2,5,3,7,101,17};
        int[] nums = {3,5,6,2,5,4,19,5,6,7,12};

        System.out.println("answer : " + process2(nums));

    }

    private static int process2(int[] nums){

        int[] arr = new int[nums.length];
        arr[0] = 1;

        for(int i=1; i<nums.length; i++){
            arr[i] = 1;
            int max = 1;
            for(int j=0; j<i; j++){

                if(nums[j] < nums[i]){
                    max = Math.max(max, arr[j] + 1);
                }
            }
            arr[i] = max;

        }

        int max = 0;
        for(int cnt : arr){
            max = Math.max(max, cnt);
        }
        return max;

    }

    private static int process(int[] nums){
        int max = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            max = Math.max(max, dfs(i, nums, map));
        }

        return max;
    }
    private static int dfs(int idx, int[] nums, HashMap<Integer, Integer> map) {
        if(map.containsKey(idx)){
            return map.get(idx);
        }

        int cnt = 1;
        for(int i=idx+1; i<nums.length; i++){
            if(nums[idx] < nums[i]){
                cnt = Math.max(cnt, 1 + dfs(i, nums, map));
            }
        }

        map.put(idx, cnt);
        return cnt;
    }
}
