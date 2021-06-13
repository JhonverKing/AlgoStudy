package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

	// write your code here

    }

    public static List<Integer>
    getMaxElementIndexes(List <Integer> a, List <Integer> rotate){
        List<Integer> Indices = new ArrayList<Integer>();


        int len = a.size();

//        int temp = a.get(0);
        for(int i=0; i<rotate.size(); i++)
        {
            int max = 0;
            int[] clone = new int[]();
            for(int j=0; j<rotate.get(i); j++){
                if(max < rotate.get(i));

                int temp = a.get(0);

                for(int k=0; k<a.size(); k++){
                    a.
                }
            }
            if(i+1==len) rotate.set(i, temp);
            else rotate.set(i, a.get(i+1));
        }


        for(int i=0; i<rotate.size(); i++) {
            if (rotate.get(i) == Collections.max(rotate)) Indices.add(i);
        }

        return Indices;
    }

    public static get
}
