module com.example.podcastapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.podcastapp to javafx.fxml;
    exports com.example.podcastapp;
}