module com.ycm.Gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;

    exports com.ycm.Gui;
    exports com.ycm.Classes;
    opens com.ycm.Classes to javafx.fxml;
    opens com.ycm.Gui to javafx.fxml;
}