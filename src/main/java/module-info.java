module com.zkurdya.javafxcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.zkurdya.javafxcalculator to javafx.fxml;
    exports com.zkurdya.javafxcalculator;
}