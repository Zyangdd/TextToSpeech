module com.example.texttospeech {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsapi;

    opens com.example.texttospeech to javafx.fxml;
    exports com.example.texttospeech;
}