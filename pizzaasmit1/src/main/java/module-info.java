module com.example.pizzaasmit1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.pizzaasmit1 to javafx.fxml;
    opens com.example.pizzaasmit1.model to javafx.base;

    exports com.example.pizzaasmit1;
}
