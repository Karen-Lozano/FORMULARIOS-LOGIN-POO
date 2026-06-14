module org.example.mitienda {

    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.mitienda to javafx.fxml;
    exports org.example.mitienda;
}