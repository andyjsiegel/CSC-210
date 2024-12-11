module com.word_search_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.word_search_gui to javafx.fxml;
    exports com.word_search_gui;
}
