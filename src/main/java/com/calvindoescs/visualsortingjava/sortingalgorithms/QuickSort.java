package com.calvindoescs.visualsortingjava.sortingalgorithms;

import com.calvindoescs.visualsortingjava.RectNode;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
public class QuickSort extends Sort {

    private static final Color PIVOT_COLOR = Color.DARKSLATEBLUE;
    private ArrayList<Transition> transitions;

    public QuickSort() {
        this.transitions = new ArrayList<>();
    }

    private void quickSort(RectNode[] arr, int lo, int hi) {
        if (lo < hi) {
            int q = partition(arr, lo, hi);
            quickSort(arr, lo, q - 1);
            quickSort(arr, q + 1, hi);
        }
    }

    //Chosen last array as pivot point
    private int partition(RectNode[] arr, int lo, int hi) {
        int i = lo;

        transitions.add(colorRectNode(arr, PIVOT_COLOR, hi));

        for (int j = lo; j < hi; j++) {
            transitions.add(colorRectNode(arr, SELECT_COLOR, j));
            if (arr[j].getValue() < arr[hi].getValue()) {
                transitions.add(swap(arr, i, j));
                transitions.add(colorRectNode(arr, START_COLOR, i));
                i++;
            } else {
                transitions.add(colorRectNode(arr, START_COLOR, j));
            }
        }
        transitions.add(swap(arr, i, hi));
        transitions.add(colorRectNode(arr, START_COLOR, i));

        return i;
    }

    @Override
    public ArrayList<Transition> startSort(RectNode[] arr) {
        this.transitions.clear();
        quickSort(arr, 0, arr.length - 1);
        transitions.add(colorRectNode(Arrays.asList(arr), SORTED_COLOR));

        return transitions;
    }
}

