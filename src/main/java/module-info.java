module com.hkrsdgroup.agileproduct {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires commons.dbutils;

    opens com.hkrsdgroup.agileproduct to javafx.fxml;
    exports com.hkrsdgroup.agileproduct;
    exports com.hkrsdgroup.agileproduct.beans;
}