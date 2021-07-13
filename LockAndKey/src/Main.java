public class Main {
    /*
    시작할수 있는 좌표부터 ( 크기 3이면 -2~2 )
    회전방향 1~4 까지 돌면서 비교
    모든방향 비교후 좌표 +1
    반복

     */

    public static void main(String[] args) {
//        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
//        int[][] lock = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}};
        int[][] key = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] lock = {{1, 1, 1, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};

        tryOpen(key, lock);
    }

    //
    public static boolean tryOpen(int[][] key, int[][] lock){
        int startOffset = (lock.length-1) * -1;
        int x=startOffset;
        int y=startOffset;
        while(true){
            for(int i=0; i<4; i++){
                if(canOpen(key, lock, x, y)) {
                    System.out.println("true");
                    return true;
                }
                else {
                    System.out.println("false");
                }
                key = rotate(key);
            }

            if(x < lock.length - 1){
                x++;
            }
            else{
                if(y <= lock.length - 1){
                    x=startOffset;
                    y++;
                }
                else
                {
                    System.out.println("범위초과");
                    return false;
                }
            }
        }
    }

    // 회전
    public static int[][] rotate(int[][] key) {
        int n = key.length;
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[j][n-i-1] = key[i][j];
            }
        }
        return result;
    }

    // 검사
    public static boolean canOpen(int[][] key, int[][] lock, int offsetX, int offsetY){


        for (int i=0; i< lock.length; i++){
            for(int j=0; j< lock.length; j++){
                // i : Y
                // j : X
                if(i >= offsetY && i < key.length + offsetY
                    && j >= offsetX && j < key.length + offsetX){
                    System.out.println("left : " + (i) + " | right : " + (j));
                    if((key[i-offsetY][j-offsetX] ^ lock[i][j]) == 0){
                        return false;
                    }
                }
                else{
                    if(lock[i][j] == 0)
                        return false;
                }
            }
        }

        return true;
    }





    public static int test(int[][] key, int[][] lock){
        int[] startPoint = new int[2];
        int[] endPoint = new int[2];

        for(int i=0; i< lock.length; i++)
        {
            for (int j=0; j< lock.length; j++){
                if(lock[i][j] == 0){
                    startPoint[0] = i;
                    startPoint[1] = j;
                    break;
                }
            }
        }

        for(int i=lock.length-1; i>=0; i--)
        {
            for (int j=lock.length-1; j>=0; j--){
                if(lock[i][j] == 0){
                    startPoint[0] = i;
                    startPoint[1] = j;
                    break;
                }
            }
        }
        return 0;
    }
}
