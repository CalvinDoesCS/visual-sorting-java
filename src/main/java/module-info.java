module com.calvindoescs.visualsortingjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.calvindoescs.visualsortingjava to javafx.fxml;
    exports com.calvindoescs.visualsortingjava;
}