참여인원 4
> 잡부 : https://github.com/lordchiwoo/algo_programmers  
> 강서 : https://github.com/mertyn88/algorithm  
> 가니 : https://github.com/kwan1989/algorithm_Programmers

진행방법
> 프로그래머스나 기타 알고리즘 문제를 각자의 방법으로 해결하고 각자 깃허브에 업로드 함.  
> 이후 디스코드 화면 공유로 문제 해결 과정에 대한 리뷰를 진행 함. 

---

## 순위
### 문제 및 풀이
[Programmers](https://programmers.co.kr/learn/courses/30/lessons/49191)  
[Git Solution](https://github.com/JhonverKing/AlgoStudy/blob/main/BoxerRank/src/Main.java)  

<details markdown="1">
  <summary>내용 보기</summary>

### 문제내용
````
n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다.
권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다.
심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다.
하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.

선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때
정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.

 - 선수의 수는 1명 이상 100명 이하입니다.
 - 경기 결과는 1개 이상 4,500개 이하입니다.
 - results 배열 각 행 [A, B]는 A 선수가 B 선수를 이겼다는 의미입니다.
 - 모든 경기 결과에는 모순이 없습니다.  
````
|n|results|return|  
|---|---|---|  
|5|[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]|2|  

### 풀이과정
##### 초기구상
1. 각 노드가 이긴 목록을 담은 HashMap 만들기  
2. 각 노드가 패배한 목록을 담은 HashMap 만들기  
3. 두 HashMap에서 각 노드의 사이즈를 더해서 n-1이면 answer++  

##### 진행하며 수정된 내용  
 - 각 노드가 배배한 목록을 담은 HashMap은 필요가 없어서 빼고함.  
 - 이긴 목록에서 패배한 목록을 찾을 수 있기 때문임.   
 - HashMap에 ArrayList를 담았다가 중복체크하는 부분에서 오래걸려서 실패함  
 - 그래서 ArrayList를 HashSet으로 바꿔서 도전 

##### 최종형태
1. `winnerHashMap` 초기 Key를 생성해둔다 - 나중에 소스안에서 있는지 비교해서 만드는거는 귀찮기때문
2. `dfs`로 해당 노드가 이기는 대상들을 찾아서 winnerHashMap에 넣는다. 이 때 HashSet으로 중복은 허용하지 않는다.
3. 더이상 이기는 대상이 없는 노드는 visited[target] = 1로 방문 완료로 처리해둔다.
4. 노드가 이기는 대상이 방문이 완료된 노드라면 대상을 Key값으로 목록을 불러와서 현재 노드에 값들을 추가해준다.
5. 그렇게 완성된 winnerHashMap에서 각 노드의 사이즈와 각 노드를 가지고 있는 목록을 카운트해서 합한 값을 배열에 저장한다.
6. 배열의 값이 n-1과 같은 노드는 answer++

##### 1차) ArrayList를 담은 HashMap - 실패
    테스트 1 〉   통과 (0.11ms, 52.2MB)  
    테스트 2 〉   통과 (0.14ms, 51.9MB)  
    테스트 3 〉   실패 (0.57ms, 52.6MB)  
    테스트 4 〉   실패 (런타임 에러)  
    테스트 5 〉   실패 (50.70ms, 59.9MB)  
    테스트 6 〉   실패 (317.93ms, 125MB)  
    테스트 7 〉   실패 (메모리 초과)  
    테스트 8 〉   실패 (메모리 초과)  
    테스트 9 〉   실패 (메모리 초과)  
    테스트 10 〉   실패 (메모리 초과)  

##### 2차) HashSet을 담은 HashMap - 성공
    테스트 1 〉	통과 (8.66ms, 53.2MB)  
    테스트 2 〉	통과 (8.64ms, 52.9MB)  
    테스트 3 〉	통과 (8.58ms, 53.1MB)  
    테스트 4 〉	통과 (9.53ms, 52.9MB)  
    테스트 5 〉	통과 (10.79ms, 52.7MB)  
    테스트 6 〉	통과 (11.87ms, 55.1MB)  
    테스트 7 〉	통과 (30.32ms, 53.9MB)  
    테스트 8 〉	통과 (36.34ms, 58.7MB)  
    테스트 9 〉	통과 (45.96ms, 57.9MB)  
    테스트 10 〉	통과 (44.41ms, 56.2MB)  


</details>


## 가장 먼 노드 (2021.06.28)  
### 문제 및 풀이  
[Programmers](https://programmers.co.kr/learn/courses/30/lessons/49189)  
[Git Solution]()  
<details markdown="1">
<summary>내용 보기</summary>
### 풀이과정

</details>


## 단어변환 (2021.06.21)  
### 문제 및 풀이  
[Programmers](https://programmers.co.kr/learn/courses/30/lessons/43163)  
[Git Solution]()  
<details markdown="1">
<summary>내용 보기</summary>

</details>


## 네트워크 (2021.06.14)  
### 문제 및 풀이
[Programmers](https://programmers.co.kr/learn/courses/30/lessons/43162)  
[Git Solution]()  
<details markdown="1">
<summary>내용 보기</summary>

</details>


## 타겟넘버 (2021.06.07)  
### 문제 및 풀이
[Programmers](https://programmers.co.kr/learn/courses/30/lessons/43165)  
[Git Solution]()  
<details markdown="1">
<summary>내용 보기</summary>

</details>

