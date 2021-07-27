import java.util.Arrays;

public class Main {
    /*
    가장 먼저 나가는 녀석을 찾고
    가장 먼저 나가는 녀석을 거치는 녀석은 다 제외
    반복
     */

    /*
    가장 빨리 진출하는 차량 순으로 정렬
    첫번째 차량이 진출하는 지점에 카메라 설치
    해당 지점을 거쳐가는 차량은 단속용 카메라를 만난것으로 처리
    다음 단속용 카메라를 만나지 않은 차량의 진출 지점에 다음 카메라를 설치
    반복~
    * */

    public static void main(String[] args) {
        int[][] routes = {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int answer = 0;

        Arrays.sort(routes, (o1, o2) -> {
            if(o1[1] == o2[1])
                return Integer.compare(o1[0], o2[0]);
            else
                return Integer.compare(o1[1], o2[1]);
        });

        System.out.println("answer : " + process2(routes));
    }


    public static int process(int[][] routes){
        int[] visited = new int[routes.length];
        int cnt = 0;
        for(int i=0; i< routes.length; i++) {
            if(visited[i] == 1) continue;

            cnt++;
            int cam = routes[i][1];
            for (int j = 0; j < routes.length; j++) {
                if (visited[j] == 1) continue;

                if (cam >= routes[j][0] && cam <= routes[j][1])
                    visited[j] = 1;
            }
        }

        return cnt;
    }

    public static int process2(int[][] routes){
        int cnt = 0;
        int cam = Integer.MIN_VALUE;
        for(int i=0; i< routes.length; i++) {
            if(cam < routes[i][0]){
                cam = routes[i][1];
                cnt++;
            }
        }
        return cnt;
    }

}
