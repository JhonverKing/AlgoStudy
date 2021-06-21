package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int answer;
        int n = 0;

        answer = getFactorial(n);

        System.out.println("answer : " + answer);
        System.out.println("recursive : " + getFactorial2(n));
    }

    public static int getFactorial(int n){
//        if(n==0) return 1;
        int sum = 1;

        for(int i=1; i<=n; i++){
            sum = sum * i;
        }

        return sum;

    }

    // 재귀
    public static int getFactorial2(int n){
        if(n == 1) return n;
        else if(n == 0) return 1;
        return getFactorial(n-1) * n;
    }
}
