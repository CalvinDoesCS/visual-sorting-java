package com.calvindoescs.visualsortingjava.sortingalgorithms;

import com.calvindoescs.visualsortingjava.RectNode;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort extends Sort {

    private static final Color MININDX_COLOR = Color.YELLOW;
    private static final Color NEW_MININDX_COLOR = Color.LIGHTGREEN;

    private ParallelTransition colorRectNode(RectNode[] arr, int a, int b, Color colorA, Color colorB) {
        ParallelTransition pt = new ParallelTransition();

        pt.getChildren().addAll(colorRectNode(arr, colorA, a), colorRectNode(arr, colorB, b));

        return pt;
    }

    @Override
    public ArrayList<Transition> startSort(RectNode[] arr) {
        ArrayList<Transition> transitions = new ArrayList<>();
        int minIndx;

        for (int i = 0; i < arr.length - 1; i++) {
            minIndx = i;
            transitions.add(colorRectNode(arr, NEW_MININDX_COLOR, minIndx));

            for (int j = i + 1; j < arr.length; j++) {
                transitions.add(colorRectNode(arr, SELECT_COLOR, j));
                if (arr[j].getValue() < arr[minIndx].getValue()) {
                    if (minIndx == i) {
                        transitions.add(colorRectNode(arr, minIndx, j, MININDX_COLOR, NEW_MININDX_COLOR));
                    } else {
                        transitions.add(colorRectNode(arr, minIndx, j, Color.CRIMSON, NEW_MININDX_COLOR));
                    }
                    minIndx = j;
                } else {
                    transitions.add(colorRectNode(arr, START_COLOR, j));
                }
            }

            if (minIndx != i) {
                transitions.add(swap(arr, i, minIndx));
            }

            transitions.add(colorRectNode(arr, START_COLOR, i, minIndx));
        }

        transitions.add(colorRectNode(Arrays.asList(arr), SORTED_COLOR));

        return transitions;
    }
}
