module com.hkrsdgroup.agileproduct {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires commons.dbutils;

    opens com.hkrsdgroup.agileproduct to javafx.fxml;
    exports com.hkrsdgroup.agileproduct;
}