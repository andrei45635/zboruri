module com.example.zboruri {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zboruri to javafx.fxml;
    exports com.example.zboruri;
}