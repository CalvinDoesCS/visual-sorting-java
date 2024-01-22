package com.calvindoescs.visualsortingjava.sortingalgorithms;

import com.calvindoescs.visualsortingjava.RectNode;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSort extends Sort{
    private boolean swapped;
    private ArrayList<Transition> transitions;

    public BubbleSort() {
        this.transitions = new ArrayList<>();
    }
    private ArrayList<Transition> compareRectNode(RectNode[] arr, int a, int b) {
        ArrayList<Transition> transitions = new ArrayList<>();

        transitions.add(colorRectNode(arr, SELECT_COLOR, a, b));

        if (arr[a].getValue() > arr[b].getValue()) {
            transitions.add(swap(arr, a, b));
            swapped = true;
        }

        transitions.add(colorRectNode(arr, START_COLOR, a, b));

        return transitions;
    }
    private void bubbleSort(RectNode[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                this.transitions.addAll(compareRectNode(arr, j, j + 1));
            }

            if (!swapped) {
                break;
            }
        }
    }
    @Override
    public ArrayList<Transition> startSort(RectNode[] arr) {
        bubbleSort(arr);
        this.transitions.add(colorRectNode(Arrays.asList(arr), SORTED_COLOR));
        return this.transitions;
    }
}
