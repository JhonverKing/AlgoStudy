public class Main {

    public static void main(String[] args) {
//        String word = "AAAAE"; // 6
//        String word = "AAAE"; // 10
//        String word = "I"; // 1563
        String word = "EIO"; // 1189

        System.out.println("answer : " + process(word));
    }


    // 1 + 1 + 1 + 1 + 2
    // 1 + 1 + 1 + (1 + 6)
    //
    private static int process(String word){
        char[] wordToCharArr = word.toCharArray();
        int len = wordToCharArr.length;
        int[] sizeOffset = {781, 156, 31, 6, 1};
        // String word = "AAAAE"; // 6
        // 1 2 3 4 5
        // A E I O U
        // (5*1) + 1 = 6
        // (5*6) + 1 = 31
        // (5*31) + 1 = 156
        // (5*156) + 1 = 781

        int seq = 0;

        int result = 0;

        while(seq<len){
            switch (wordToCharArr[seq]){
                case 'A': result += sizeOffset[seq] * 0 + 1; break;
                case 'E': result += sizeOffset[seq] * 1 + 1; break;
                case 'I': result += sizeOffset[seq] * 2 + 1; break;
                case 'O': result += sizeOffset[seq] * 3 + 1; break;
                case 'U': result += sizeOffset[seq] * 4 + 1; break;
            }
            seq++;
        }

        return result;
    }
}
