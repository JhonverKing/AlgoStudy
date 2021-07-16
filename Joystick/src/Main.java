public class Main {

    public static void main(String[] args) {
        String name = "JEROEN";
//        String name = "JAN";

        System.out.println("answer : " + getMinimumStep(name));


    }
    public static int getMinimumStep(String name){

        char[] nameArray = name.toCharArray();
        int currentIndex = 0;
        int result = 0; // 결과

        for(int i=0; i<nameArray.length; i++){
            char currentChar = nameArray[currentIndex];

            // 바꿀 알파뱃에서 A까지의 거리를 구해서 result에 누적
            result += ('Z' - currentChar + 1) < (currentChar - 'A') ? ('Z' - currentChar + 1) : (currentChar - 'A');
            nameArray[currentIndex] = 'A';

            // 바꿀 위치의 방향과 거리를 찾아서 result에 누적
            for(int j=1; j<nameArray.length; j++){
                int leftDirectionIndex = (currentIndex + j) % nameArray.length;
                int rightDirectionIndex = (currentIndex - j + nameArray.length) % nameArray.length;
                int nextIndex = nameArray[leftDirectionIndex] != 'A' ? leftDirectionIndex : rightDirectionIndex;
                if(nameArray[nextIndex] != 'A'){
                    result += j;
                    currentIndex = nextIndex;
                    break;
                }
            }
        }
        return result;
    }
}
