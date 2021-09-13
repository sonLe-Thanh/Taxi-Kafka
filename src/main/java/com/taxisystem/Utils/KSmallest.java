package com.taxisystem.Utils;

import com.taxisystem.Models.Pair;
import com.taxisystem.Models.Pair_sort;
import com.taxisystem.Models.Sort;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class KSmallest {
    public static ArrayList<Integer> getKSmallestIndices(JSONArray arr, int k){
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        for (int i =1; i<arr.length();i++){
            priorityQueue.add(new Pair(i, arr.getDouble(i)));
        }

        for (int i=0;i<k;i++){
            Pair nextSmallest = priorityQueue.poll();
            assert nextSmallest != null;
            result.add(nextSmallest.getIndex());
        }
        return result;
    }

    public static ArrayList<Integer> getKSmallestIndices_sort(JSONArray arr, int k){
        ArrayList<Integer> result = new ArrayList<>();
        int length = arr.length();
        Pair_sort[] arr_result = new Pair_sort[length-1];
        for (int i=0; i<length-1;i++){
            arr_result[i] = new Pair_sort(i+1,arr.getDouble(i));
        }
        return Sort.compare(arr_result, k);
    }
}