package com.example.task_native.view;

import java.util.ArrayList;
import java.util.List;

public   class  Solution {
    static   int aPoint =0 , bPoint = 0;

    long[] ar  = {100000,155555,14444};


    static List<Integer> solution = new ArrayList<>();
    // Complete the compareTriplets function below.
    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        for(int i=0; i<a.size(); i++){
            if(a.get(i) > b.get(i)){
                aPoint += 1;
            }else if(a.get(i) <b.get(i)){
                bPoint += 1;
            }else if(a.get(i) == b.get(i)){
                aPoint += 0;
                bPoint += 0;
            }
        }

        if(aPoint != 0){
            solution.add(aPoint);
        }
        if(bPoint != 0){
            solution.add(bPoint);
        }


        return solution;

    }
}