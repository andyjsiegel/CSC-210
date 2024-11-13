module com.gardengui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gardengui to javafx.fxml;
    exports com.gardengui;
}
