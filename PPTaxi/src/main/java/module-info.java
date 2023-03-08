module com.example.pptaxi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pptaxi to javafx.fxml;
    exports com.example.pptaxi;
}