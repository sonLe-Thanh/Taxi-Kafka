package com.taxisystem.Utils;

import com.taxisystem.Models.Pair;
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
}