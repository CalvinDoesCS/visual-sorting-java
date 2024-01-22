package com.calvindoescs.visualsortingjava.sortingalgorithms;

public enum SortAlgorithms {
    BUBBLE_SORT("Bubble Sort"),
    INSERTION_SORT("Insertion Sort"),
    QUICK_SORT("Quick Sort");

    private final String displayName;

    SortAlgorithms(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
