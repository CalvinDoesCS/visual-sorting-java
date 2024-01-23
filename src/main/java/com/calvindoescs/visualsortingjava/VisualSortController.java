package com.calvindoescs.visualsortingjava;

import com.calvindoescs.visualsortingjava.sortingalgorithms.*;
import com.calvindoescs.visualsortingjava.util.RandomRectNode;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VisualSortController implements Initializable {


    @FXML
    private ChoiceBox<SortAlgorithms> sortChoice;
    @FXML
    private Button startBtn;
    @FXML
    private Button newArrayBtn;
    @FXML
    private Slider sizeSlider;
    @FXML
    private Slider speedSlider;
    @FXML
    private Pane mainPane;


    private static double mainPaneWidth;
    private static double mainPaneHeight;
    private static int rectNodesSize = 50;

    private static int durationInMs = 50;
    private RectNode[] rectNodes;
    private List<Sort> abstractSortList;
    private boolean isAnimationRunning = false;
    private boolean isAnimationFinished = true;
    private SequentialTransition sq;
    private SortAlgorithms currSort = SortAlgorithms.BUBBLE_SORT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortChoice.setValue(SortAlgorithms.BUBBLE_SORT);
        sortChoice.getItems().addAll(SortAlgorithms.values());
        sortChoice.setOnAction(this::getSortChoice);

        sizeSlider.setValue(rectNodesSize);
        sizeSlider.valueProperty().addListener((observableValue, number, t1) -> {
            rectNodesSize = (int) sizeSlider.getValue();
            generateAndAddRectNodesOnScene();
        });

        speedSlider.setValue(durationInMs); //in ms
        speedSlider.valueProperty().addListener(((observableValue, number, t1) -> {
            durationInMs = (int) speedSlider.getValue();
        }));


        abstractSortList = new ArrayList<>();
        abstractSortList.add(new BubbleSort());
        abstractSortList.add(new InsertionSort());
        abstractSortList.add(new QuickSort());
        abstractSortList.add(new MergeSort());
        abstractSortList.add(new SelectionSort());
        abstractSortList.add(new HeapSort());
    }

    public void getSortChoice(ActionEvent e) {
        currSort = sortChoice.getValue();
    }

    public static int getDurationInMs() {
        return durationInMs;
    }

    public static void setDurationInMs(int durationInMs) {
        VisualSortController.durationInMs = durationInMs;
    }

    public static double getMainPaneWidth() {
        return mainPaneWidth;
    }

    public static int getRectNodesSize() {
        return rectNodesSize;
    }

    public static void setRectNodesSize(int rectNodesSize) {
        VisualSortController.rectNodesSize = rectNodesSize;
    }

    public static void setMainPaneWidth(double mainPaneWidth) {
        VisualSortController.mainPaneWidth = mainPaneWidth;
    }

    public static double getMainPaneHeight() {
        return mainPaneHeight;
    }

    public static void setMainPaneHeight(double mainPaneHeight) {
        VisualSortController.mainPaneHeight = mainPaneHeight;
    }

    public void generateAndAddRectNodesOnScene() {
        resetVariables();
        generateNewRectNodes();
        addRectNodesOnScene();
    }

    private void addRectNodesOnScene() {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(rectNodes);
    }

    private void generateNewRectNodes() {
        getPaneWidthHeight();
        this.rectNodes = RandomRectNode.randomRectNodes(rectNodesSize);
    }

    public void recalculateRectNodeSize() {
        getPaneWidthHeight();
        RandomRectNode.recalculateRectNodeSize(this.rectNodes);
        addRectNodesOnScene();
    }

    public void getPaneWidthHeight() {
        mainPaneWidth = mainPane.getWidth();
        mainPaneHeight = mainPane.getHeight();
    }

    public void setButtonsDisable(boolean isDisabled) {
        newArrayBtn.setDisable(isDisabled);
        sizeSlider.setDisable(isDisabled);
        speedSlider.setDisable(isDisabled);
    }

    @FXML
    public void onClickedStartBtn() {
        if (isAnimationRunning) {
            setButtonsDisable(false);
            isAnimationRunning = false;
            sq.pause();
            startBtn.setText("Unpause Animation");
            return;
        }
        if (!isAnimationFinished) {
            isAnimationRunning = true;
            setButtonsDisable(true);
            sq.play();
            startBtn.setText("Pause Animation");
            return;
        }

        sq = new SequentialTransition();
        sq.getChildren().addAll(abstractSortList.get(currSort.ordinal()).startSort(rectNodes));
        sq.setOnFinished(e -> {
            sortChoice.setDisable(false);
            isAnimationFinished = true;
            isAnimationRunning = false;
            startBtn.setText("Start");
            setButtonsDisable(false);
            sortChoice.setDisable(false);
        });
        setButtonsDisable(true);
        sortChoice.setDisable(true);
        startBtn.setText("Pause Animation");

        isAnimationRunning = true;
        isAnimationFinished = false;
        sq.playFromStart();


    }

    @FXML
    public void onClickedNewArrayBtn() {
        generateAndAddRectNodesOnScene();
    }

    public void resetVariables() {
        isAnimationRunning = false;
        isAnimationFinished = true;
        sortChoice.setDisable(false);
        startBtn.setText("Start");
    }
}