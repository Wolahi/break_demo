module com.example.break_house_demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.example.break_house_demo to javafx.fxml;
    exports com.example.break_house_demo;
}