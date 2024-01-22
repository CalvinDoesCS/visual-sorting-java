package com.calvindoescs.visualsortingjava;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RectNode extends Rectangle {
    private int value;

    public RectNode(int n) {
        this.value = n;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //This method provides the transitions of the node to a new x value
    public TranslateTransition moveX(double x) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(this);
        t.setDuration(Duration.millis(VisualSortController.getDurationInMs()));
        t.setByX(x);
        return t;
    }
}
