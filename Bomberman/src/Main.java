import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int n = 5;
        List<String> grid = new ArrayList<>();
//        grid.add(".......");
//        grid.add("...O...");
//        grid.add("....O..");
//        grid.add(".......");
//        grid.add("OO.....");
//        grid.add("OO.....");

        grid.add(".......");
        grid.add("...O.O.");
        grid.add("....O..");
        grid.add("..O....");
        grid.add("OO...OO");
        grid.add("OO.O...");

        bomberMan(n, grid);
    }



    public static List<String> bomberMan(int n, List<String> grid){

        char[][] locations = new char[grid.size()][grid.get(0).length()];

        if(n > 4) {
            n = (n % 4) + 4;
        }

        for(int i=1; i<=n; i++){
            System.out.println(i + "sec...");

            if(i % 2 == 0){
                // installation
                System.out.println("installation");
                installation(grid);
            }
            if(i % 2 == 1){
                //exlposion
                if(i != 1){
                    System.out.println("exlposion");
                    explosion(grid, locations);
                }

                // locations
                System.out.println("locations");
                locations = locations(grid);
            }

            for(String s : grid){
                System.out.println("s : " + s);
            }
            System.out.println("");

        }
        return grid;
    }
    public static void installation(List<String> grid){

        char[] line = new char[grid.get(0).length()];
        Arrays.fill(line, 'O');
        for(int i=0; i<grid.size(); i++){
            grid.set(i,new String(line));
        }


    }

    public static char[][] locations(List<String> grid){
        char[][] locations = new char[grid.size()][grid.get(0).length()];

        for(int i=0; i<grid.size(); i++){
            locations[i] = grid.get(i).toCharArray();
        }
        return locations;
    }

    public static void explosion(List<String> grid, char[][] locations){

        for(int i=0; i<locations.length; i++){
            char[] line = grid.get(i).toCharArray();
            for(int j=0; j<locations[i].length; j++){

                for(int offsetY = -1; offsetY<2; offsetY++){
                    if(!isValid(i, offsetY, locations.length)) continue;
                    if(locations[i+offsetY][j] == 'O'){
                        line[j] = '.';
                        break;
                    }
                }
                for(int offsetX = -1; offsetX<2; offsetX++){
                    if(!isValid(j, offsetX, locations.length)) continue;
                    if(locations[i][j+offsetX] == 'O'){
                        line[j] = '.';
                        break;
                    }
                }
            }
            grid.set(i, new String(line));
        }
    }

    private static boolean isValid(int loopNo, int offset, int length){

        if(loopNo == 0 && offset == -1){
            return false;
        }
        if(loopNo == length-1 && offset == 1){
            return false;
        }

        return true;
    }
}