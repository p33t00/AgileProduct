module com.hkrsdgroup.agileproduct {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.hkrsdgroup.agileproduct to javafx.fxml;
    exports com.hkrsdgroup.agileproduct;
}