public class Main {

    public static void main(String[] args) {
	// write your code here

//        char[][] grid = {{'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}};
        char[][] grid = {{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};

        int answer = process(grid);

        System.out.println("answer : " + answer);

    }

    public static int process(char[][] grid){

        int row = grid.length;
        int col = grid[0].length;
        int result = 0;

        for(int i=0; i<row; i++){


            for(int j=0; j<col; j++){

                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    result++;
                }
            }
        }

        return result;
    }

    public static void dfs(char[][] grid, int r, int c){
        grid[r][c] = '0';

        for(int i=0; i<4; i++){
            int adjRowIndex = getAdjRowIndex(r, i);
            int adjColIndex = getAdjColIndex(c, i);

            if(adjRowIndex >= 0 && adjRowIndex < grid.length
            && adjColIndex >= 0 && adjColIndex < grid[0].length){
                if(grid[adjRowIndex][adjColIndex] == '1'){
                    dfs(grid, adjRowIndex, adjColIndex);
                }
            }
        }

    }

    public static int getAdjRowIndex(int r, int i){
        switch(i){
            case 0:
                return r + 1;
            case 1:
                return r + -1;
        }
        return r;
    }
    public static int getAdjColIndex(int c, int i){
        switch(i){
            case 2:
                return c + 1;
            case 3:
                return c + -1;
        }
        return c;
    }


}
