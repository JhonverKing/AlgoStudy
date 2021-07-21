import java.util.*;

public class Main {

    public static void main(String[] args) {
        int n = 5;
//        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {
//                4, 2, 1, 1}, {3, 2, 1, 1}};
        int[][] build_frame = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {
                3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};

        int[][] answer = process(n, build_frame);
        answerSort(answer);

        for(int i=0; i<answer.length; i++){
            System.out.println(answer[i][0] + "," + answer[i][1] + "," + answer[i][2]);
        }

    }

    public static int[][] process(int n, int[][] build_frame){
        HashSet<int[]> installedFrames = new HashSet<int[]>();

        for(int[] frame : build_frame){
            // 설치
            if(frame[3] == 1){
                int[] newFrame = new int[]{frame[0], frame[1], frame[2]};
                if(canInstall(newFrame, installedFrames)){
                    installedFrames.add(newFrame);
                }
            }
            // 삭제
            if(frame[3] == 0){
                if(installedFrames.contains(frame)) System.out.println("test");
                for(int[] curFrame : installedFrames){
                    if(curFrame[0] == frame[0] && curFrame[1] == frame[1] && curFrame[2] == frame[2]) {
                        if(canRemove(curFrame, installedFrames)){
                            installedFrames.remove(curFrame);
                            break;
                        }
                    }
                }
            }
        }
        int[][] result = new int[installedFrames.size()][3];
        installedFrames.toArray(result);

        return result;
    }

    public static boolean canInstall (int[] frame, HashSet<int[]> installedFrames) {
        int x = frame[0];
        int y = frame[1];
        int a = frame[2]; // 0:기둥 // 1:보
        int leftAndRightCheck = 0; // 2가되면 양쪽이 보인것으로 확인

        // 바닥에 기둥
        if(a==0 && y==0) return true;
        for(int[] installedFrame : installedFrames){
            // 밑에 자리에 기둥이 있는지
            boolean downIsColmun = installedFrame[0] == x && installedFrame[1] == y-1 && installedFrame[2] == 0;
            // 오른쪽 밑에 자리에 기둥이 있는지
            boolean downRightIsColmun = installedFrame[0] == x+1 && installedFrame[1] == y-1 && installedFrame[2] == 0;
            // 왼쪽 자리에 보가 있는지
            boolean leftIsBeam = installedFrame[0] == x-1 && installedFrame[1] == y && installedFrame[2] == 1;
            // 현재 자리에 보가 있는지
            boolean thisIsBeam = installedFrame[0] == x && installedFrame[1] == y && installedFrame[2] == 1;
            // 오른쪽 자리에 보가 있는지
            boolean rightIsBeam = installedFrame[0] == x+1 && installedFrame[1] == y && installedFrame[2] == 1;
            // 왼쪽이나 오른쪽 자리가 보면 +1
            if(leftIsBeam || rightIsBeam) leftAndRightCheck++;

            // 기둥 가능한지 check
            if(a == 0 && (downIsColmun || leftIsBeam || thisIsBeam)) {
                return true;
            }
            // 보 가능한지 check
            if(a == 1 && (downIsColmun || downRightIsColmun || leftAndRightCheck == 2)) {
                return true;
            }
        }
        return false;
    }

    // 지웠을때 설치된 나머지 프레임들이 유효한지 검사
    public static boolean canRemove (int[] frame, HashSet<int[]> origin) {
        HashSet<int[]> cloneFrames = (HashSet<int[]>) origin.clone();

        // 설치된 프레임을 복제하여 대상 프레임을 제거한다.
        cloneFrames.remove(frame);

        // 제거한 목록에서 각 프레임들이 유효한지 검사한다.
        for(int[] cloneFrame : cloneFrames){
            if(!canInstall(cloneFrame, cloneFrames))
                return false;
        }

        return true;
    }

    // 앞자리부터 우선 순위로 정렬
    public static void answerSort(int[][] answer) {
        Arrays.sort(answer, (o1, o2) -> {
            if(o1[0] == o2[0] && o1[1] != o2[1]){
                return Integer.compare(o1[1], o2[1]);
            }
            else if(o1[0] == o2[0] && o1[1] == o2[1]){
                return Integer.compare(o1[2], o2[2]);
            }
            else {
                return Integer.compare(o1[0], o2[0]);
            }
        });
    }

}
