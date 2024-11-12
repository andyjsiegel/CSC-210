module com.foodapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.foodapp to javafx.fxml;
    exports com.foodapp;
}
