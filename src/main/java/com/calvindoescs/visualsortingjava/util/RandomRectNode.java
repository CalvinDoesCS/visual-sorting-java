package com.calvindoescs.visualsortingjava.util;

import com.calvindoescs.visualsortingjava.RectNode;
import com.calvindoescs.visualsortingjava.VisualSortController;
import javafx.scene.paint.Color;

public class RandomRectNode {

    public RandomRectNode() {
    }
    public static RectNode[] randomRectNodes(int n) {
        RectNode[] arr = new RectNode[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new RectNode((int) (Math.random() * VisualSortController.mainPaneHeight));
            arr[i].setX(i * (VisualSortController.mainPaneWidth/ arr.length));
            arr[i].setY(VisualSortController.mainPaneHeight - arr[i].getValue());
            arr[i].setFill(Color.LIGHTBLUE);
            setRectNodeDim(arr[i], arr.length);
        }
        return arr;

    }
    public static void recalculateRectNodeSize(RectNode[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i].setX(i * (VisualSortController.mainPaneWidth/ arr.length));
            arr[i].setY(VisualSortController.mainPaneHeight - arr[i].getValue());
            arr[i].setFill(Color.LIGHTBLUE);
            setRectNodeDim(arr[i], arr.length);
        }
    }
    private static void setRectNodeDim(RectNode rectNode, int n) {
        rectNode.setWidth(VisualSortController.mainPaneWidth / n );
        rectNode.setHeight(rectNode.getValue());
    }
}

