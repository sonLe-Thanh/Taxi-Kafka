package com.taxisystem.Models;

import org.jetbrains.annotations.NotNull;

public class Pair implements Comparable<Pair> {
    private final int index;
    private final double value;

    public Pair(int index, double value){
        this.value = value;
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public int compareTo(@NotNull Pair o) {
        return Double.compare(this.value, o.value);
    }
}
