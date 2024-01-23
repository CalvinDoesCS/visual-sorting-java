package com.calvindoescs.visualsortingjava.sortingalgorithms;

import com.calvindoescs.visualsortingjava.RectNode;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort extends Sort {
    private static final Color ROOT_COLOR = Color.GOLD;
    private ArrayList<Transition> transitions;

    public HeapSort() {
        this.transitions = new ArrayList<>();
    }

    private void heapify(RectNode[] arr, int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;

        if (left < n && arr[max].getValue() < arr[left].getValue()) {
            max = left;
        }

        if (right < n && arr[max].getValue() < arr[right].getValue()) {
            max = right;
        }

        if (max != i) {
            transitions.add(swap(arr, i, max));
            heapify(arr, max, n);
        }

    }

    private void heapSort(RectNode[] arr) {
        //build the max heap
        transitions.add(colorRectNode(selectSubTree(arr, arr.length), SELECT_COLOR));
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        transitions.add(colorRectNode(selectSubTree(arr, arr.length), START_COLOR));

        //swap root node with final and heapify the sub array
        for (int i = arr.length - 1; i > 0; i--) {
            transitions.add(colorRectNode(arr, ROOT_COLOR, 0));
            transitions.add(swap(arr, 0, i));
            transitions.add(colorRectNode(arr, START_COLOR, i));
            transitions.add(colorRectNode(selectSubTree(arr, i), SELECT_COLOR));
            heapify(arr, 0, i);
            transitions.add(colorRectNode(selectSubTree(arr, i), START_COLOR));
        }
    }

    private ArrayList<RectNode> selectSubTree(RectNode[] arr, int n) {

        return new ArrayList<>(Arrays.asList(arr).subList(0, n));
    }

    @Override
    public ArrayList<Transition> startSort(RectNode[] arr) {
        this.transitions.clear();
        heapSort(arr);
        transitions.add(colorRectNode(Arrays.asList(arr), SORTED_COLOR));
        return transitions;
    }
}