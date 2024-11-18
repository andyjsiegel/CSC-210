module com.paint {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.paint to javafx.fxml;
    exports com.paint;
}
