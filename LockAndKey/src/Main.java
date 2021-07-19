public class Main {
    /*
    초기구상
        현재 위치에서 90도씩 회전하면서 열쇠가 맞는지 확인.
        현재 위치를 1씩 변경시켜서 전체 범위를 탐색 반복
        열쇠와 자물쇠가 최소 한칸 이상 겹쳐야함
     */

    public static void main(String[] args) {
//        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
//        int[][] lock = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 1}, {1, 0, 1, 1}};
        int[][] key = {{1, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        int[][] lock = {{1, 1, 1, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};

        boolean answer = true;
        answer = openProcess(key, lock);

        System.out.println("answer : " + answer);
    }

    //
    public static boolean openProcess(int[][] key, int[][] lock){
        int startPosition = (key.length-1) * -1;
        int positionX = startPosition;
        int positionY = startPosition;

        // r x y
        int[][][] rotateKeySet = getRotatedKeySet(key);

        while(true){
            // 방향별로 열쇠가 맞는지 확인
            for(int i=0; i<4; i++){
                if(canOpen(key, lock, positionX, positionY)) {
                    return true;
                }
                // key = getRotatedKey(key); // 90도씩 회전
                key = rotateKeySet[i]; // 미리 만들어둔 회전셋 사용하기
            }

            // 열쇠를 이동한다
            if(positionX < lock.length - 1){
                positionX++;
            }
            else if(positionY < lock.length - 1){
                positionX = startPosition;
                positionY++;
            }
            else{
                return false;
            }
        }
    }

    // key를 90도 회전한다.
    public static int[][] getRotatedKey(int[][] key) {
        int n = key.length;
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[j][n-i-1] = key[i][j];
            }
        }
        return result;
    }

    // key를 0도, 90도, 180도, 270도 회전한 3차원 배열을 만든다.
    public static int[][][] getRotatedKeySet(int[][] key) {
        int n = key.length;
        int[][][] result = new int[4][n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[0][i][j] = key[i][j]; // 0도
                result[1][j][n-i-1] = key[i][j]; // 90도
                result[2][j][n-i-1] = key[n-j-1][i]; // 180도
                result[3][j][n-i-1] = key[n-i-1][n-j-1]; // 270도
            }
        }
        return result;
    }

    // 지정된 위치에서 key가 lock을 열 수 있는지 확인한다.
    public static boolean canOpen(int[][] key, int[][] lock, int positionX, int positionY){

        for (int i=0; i< lock.length; i++){
            for(int j=0; j< lock.length; j++){
                // 자물쇠의 칸이 열쇠와 겹치는 부분을 XOR
                if(i >= positionY && i < positionY + key.length
                        && j >= positionX && j < positionX + key.length){
                    if((key[i-positionY][j-positionX] ^ lock[i][j]) == 0){
                        return false;
                    }
                }
                // 열쇠와 겹치지 않는 부분에 빈곳이 없는지 체크한다.
                else if(lock[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
}
