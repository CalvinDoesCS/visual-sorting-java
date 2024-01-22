package com.calvindoescs.visualsortingjava.sortingalgorithms;

import com.calvindoescs.visualsortingjava.RectNode;
import com.calvindoescs.visualsortingjava.VisualSortController;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
public abstract class Sort {
    final Color START_COLOR = Color.LIGHTBLUE;
    final Color SELECT_COLOR = Color.CYAN;
    final Color SORTED_COLOR = Color.ROYALBLUE;
    ParallelTransition colorRectNode(RectNode[] arr, Color color, int...a) {
        ParallelTransition pt = new ParallelTransition();

        for (int i = 0; i < a.length; i++) {
            FillTransition fillTransition= new FillTransition();
            fillTransition.setShape(arr[a[i]]);
            fillTransition.setToValue(color);
            fillTransition.setDuration(Duration.millis(100));
            pt.getChildren().add(fillTransition);
        }
        return pt;
    }
    ParallelTransition colorRectNode(List<RectNode> list, Color color) {
        ParallelTransition parallelTransition = new ParallelTransition();

        for (RectNode c : list) {
            FillTransition fillTransition = new FillTransition();
            fillTransition.setShape(c);
            fillTransition.setToValue(color);
            fillTransition.setDuration(Duration.millis(100));
            parallelTransition.getChildren().add(fillTransition);
        }

        return parallelTransition;
    }
    ParallelTransition swap(RectNode[] arr, int i, int j) {
        ParallelTransition parallelTransition = new ParallelTransition();
        double dx = (VisualSortController.mainPaneWidth / VisualSortController.rectNodesSize);
        int dxFactor = j - i;
        parallelTransition.getChildren().addAll(arr[i].moveX(dx * dxFactor), arr[j].moveX(-dx * dxFactor));

        RectNode tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        return parallelTransition;
    }
    public abstract ArrayList<Transition> startSort(RectNode[] arr);
}
