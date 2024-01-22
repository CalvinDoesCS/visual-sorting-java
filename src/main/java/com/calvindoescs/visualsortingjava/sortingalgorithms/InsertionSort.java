package com.calvindoescs.visualsortingjava.sortingalgorithms;

import com.calvindoescs.visualsortingjava.RectNode;
import com.calvindoescs.visualsortingjava.VisualSortController;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertionSort extends Sort{
    private ArrayList<Transition> transitions;

    public InsertionSort() {
        this.transitions = new ArrayList<>();
    }
    @Override
    public ArrayList<Transition> startSort(RectNode[] arr) {
        this.transitions.clear();
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            RectNode key = arr[i];
            int keyIndx = i;

            ParallelTransition pt = new ParallelTransition();

            transitions.add(colorRectNode(arr, SELECT_COLOR, i));

            double dx = (VisualSortController.getMainPaneWidth() / VisualSortController.getRectNodesSize());
            while(j >= 0 && arr[j].getValue() > key.getValue()) {
                pt.getChildren().add(arr[j].moveX(dx));
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;

            pt.getChildren().add(key.moveX(dx * (j + 1 - i)));
            transitions.add(pt);
            transitions.add(colorRectNode(arr, START_COLOR, j + 1));

        }

        transitions.add(colorRectNode(Arrays.asList(arr), SORTED_COLOR));

        return transitions;

    }
}
