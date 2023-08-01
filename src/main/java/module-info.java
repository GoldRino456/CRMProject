module com.RealBusinessCRM {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.RealBusinessCRM to javafx.fxml;
    exports com.RealBusinessCRM;
}