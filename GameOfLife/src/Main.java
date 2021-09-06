public class Main {
/*
살아있는 이웃이 2개 미만인 살아있는 세포는 마치 인구 부족으로 인한 것처럼 죽습니다.
2~3개의 살아있는 이웃이 있는 살아있는 세포는 다음 세대에 계속 살아갑니다.
3개 이상의 살아있는 이웃이 있는 살아있는 세포는 마치 인구 과잉으로 인해 죽습니다.
정확히 세 개의 살아있는 이웃이 있는 죽은 세포는 마치 번식에 의한 것처럼 살아있는 세포가 됩니다.
 */
    public static void main(String[] args) {
	// write your code here
//        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int[][] board = {{1,1},{1,0}};
        int[][] answer = process(board);
        for(int i=0; i< board.length; i++){
            board[i] = answer[i].clone();
        }

        for(int i=0; i< board.length; i++){
            for(int j=0; j<board[0].length; j++){
                System.out.print(board[i][j] + ", ");
            }
            System.out.println("");
        }
    }

    private static int[][] process(int[][] board){
        int m = board[0].length; // X
        int n = board.length; // Y
        int[][] result = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int aliveAdjCnt = getAliveAdjCnt(board, j, i);
                if(board[i][j] == 1 && (aliveAdjCnt < 2 || aliveAdjCnt > 3)){
                    result[i][j] = 0;
                }
                else if(board[i][j] == 0 && (aliveAdjCnt == 3)){
                    result[i][j] = 1;
                }
                else{
                    result[i][j] = board[i][j];
                }

            }
        }

        return result;
    }

    private static int getAliveAdjCnt(int[][] board, int x, int y){
        int m = board[0].length;
        int n = board.length;
        int cnt = 0;

        for(int i=y-1; i<=y+1; i++){
            if(!isValid(i, n))
                continue;

            for(int j=x-1; j<=x+1; j++){
                if(!isValid(j, m))
                    continue;

                if(i==y && j==x)
                    continue;
                if(board[i][j] == 1)
                    cnt++;
            }
        }

        return cnt;
    }

    private static boolean isValid(int idx, int len){
        if(idx >= 0 && idx < len)
            return true;
        return false;
    }

}
