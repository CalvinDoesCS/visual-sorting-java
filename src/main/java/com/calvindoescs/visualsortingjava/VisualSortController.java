package com.calvindoescs.visualsortingjava;

import com.calvindoescs.visualsortingjava.sortingalgorithms.BubbleSort;
import com.calvindoescs.visualsortingjava.sortingalgorithms.Sort;
import com.calvindoescs.visualsortingjava.util.RandomRectNode;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;

public class VisualSortController implements Initializable {


    @FXML
    public ChoiceBox<String> sortChoice;
    @FXML
    public Button startBtn;
    @FXML
    public Button newArrayBtn;
    @FXML
    public Slider sizeSlider;
    //FXML elements
    @FXML
    Pane mainPane;


    //Non
    public static double mainPaneWidth;
    public static double mainPaneHeight;

    public static int rectNodesSize = 50;
    private RectNode[] rectNodes;
    private String[] sortOptions = {"Bubble Sort", "Insertion Sort"};
    private String currSort = sortOptions[0];
    List<Sort> abstractSortList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sortChoice.setValue("Bubble Sort");
        sortChoice.getItems().addAll(sortOptions);
        sortChoice.setOnAction(this::getSortChoice);

        sizeSlider.setValue(rectNodesSize);
        sizeSlider.valueProperty().addListener((observableValue, number, t1) -> {
            rectNodesSize = (int) sizeSlider.getValue();
            generateAndAddRectNodesOnScene();
        });

        abstractSortList = new ArrayList<>();
        abstractSortList.add(new BubbleSort());
    }
    public void getSortChoice(ActionEvent e){
        currSort = sortChoice.getValue();
    }
    public void generateAndAddRectNodesOnScene(){
        generateNewRectNodes();
        addRectNodesOnScene();
    }
    private void addRectNodesOnScene(){
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(rectNodes);
    }
    private void generateNewRectNodes(){
        getPaneWidthHeight();
        this.rectNodes = RandomRectNode.randomRectNodes(rectNodesSize);
    }
    public void recalculateRectNodeSize(){
        getPaneWidthHeight();
        RandomRectNode.recalculateRectNodeSize(this.rectNodes);
        addRectNodesOnScene();
    }
    public void getPaneWidthHeight(){
        mainPaneWidth = mainPane.getWidth();
        mainPaneHeight = mainPane.getHeight();
    }
    public void setButtonsDisable(boolean bool){
        startBtn.setDisable(bool);
        newArrayBtn.setDisable(bool);
        sizeSlider.setDisable(bool);
    }
    @FXML
    public void onClickedStartBtn(){
        SequentialTransition sq = new SequentialTransition();

        sq.getChildren().addAll(abstractSortList.get(0).startSort(rectNodes));

        sq.setOnFinished(e -> {
            setButtonsDisable(false);
        });
        setButtonsDisable(true);
        sq.play();
    }
    @FXML
    public void onClickedNewArrayBtn(){
        generateAndAddRectNodesOnScene();
    }
}