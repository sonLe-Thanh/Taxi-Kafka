package com.taxisystem.Models;
import java.util.*;

public class Sort {
    public static ArrayList<Integer> compare(Pair_sort[] arr, int k) {
        Arrays.sort(arr, Comparator.comparingDouble(o -> o.value));
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(arr[i].idx);
        }
        return result;
    }
}
